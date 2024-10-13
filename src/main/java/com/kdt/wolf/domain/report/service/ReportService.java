package com.kdt.wolf.domain.report.service;

import com.kdt.wolf.domain.group.dao.GroupMemberDao;
import com.kdt.wolf.domain.group.dao.GroupPostDao;
import com.kdt.wolf.domain.group.dao.QuestionBoardDao;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.QuestionBoardEntity;
import com.kdt.wolf.domain.group.entity.QuestionCommentEntity;
import com.kdt.wolf.domain.report.dao.ReportAdminDto.ReportDetailDto;
import com.kdt.wolf.domain.report.dao.ReportAdminDto.ReportPreviewDto;
import com.kdt.wolf.domain.report.dao.ReportDao;
import com.kdt.wolf.domain.report.dto.ReportDto.CreateReportRequest;
import com.kdt.wolf.domain.report.entity.ReportCategoryEntity;
import com.kdt.wolf.domain.report.entity.ReportEntity;
import com.kdt.wolf.domain.report.entity.ReportTopic;
import com.kdt.wolf.domain.user.dao.UserDao;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.fcm.service.FcmService;
import com.kdt.wolf.global.fcm.service.dto.FCMNotificationRequestDto;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReportService {
    private final UserDao userDao;
    private final ReportDao reportDao;
    private final GroupPostDao groupPostDao;
    private final GroupMemberDao groupMemberDao;
    private final QuestionBoardDao questionBoardDao;
    private final FcmService fcmService;

    public Long createReport(CreateReportRequest request, Long reporterId) {
        ReportEntity report = createReportEntity(request, reporterId);

        return reportDao.save(report).getReportId();
    }

    @Transactional
    public Long WarningReport(Long reportId) {
        ReportEntity report = reportDao.findById(reportId);
        //유저 : 경고 알림 / 그룹 : 삭제 및 알림 / 댓글 : 삭제 및 알림
        //TODO : 리팩토링 필요
        if(report.getTopic() == ReportTopic.USER){
            // 경고 알림 보내기
            sendNotification(report.getReportedUser().getUserId(), "신고를 받아 경고를 받았습니다.");
        }else if(report.getTopic() == ReportTopic.GROUP){
            // 그룹원들에게 메세지 보내기
            groupMemberDao.findAllByGroupId(report.getReportedGroupPost().getGroupPostId())
                    .forEach(groupMember -> {
                        sendNotification(groupMember.getUser().getUserId(), "신고를 받아 그룹이 삭제되었습니다.");
                    });
            // 그룹 삭제
            groupPostDao.delete(report.getReportedGroupPost());
        } else if(report.getTopic() == ReportTopic.REPLY){
            // 댓글 삭제
            report.getReportedReply().updateCommentDetail("삭제된 댓글입니다.");
            // 댓글 작성자에게 알림 보내기
            sendNotification(report.getReportedReply().getAuthor().getUserId(), "신고를 받아 댓글이 삭제되었습니다.");
        } else if (report.getTopic() == ReportTopic.QUESTION){
            // 질문 삭제
            report.getReportedQuestion().updateQuestionDetail("삭제된 질문입니다.");
        }
        processReport(report);
        return reportId;
    }

    public List<ReportPreviewDto> findAllReports() {
        List<ReportEntity> reports = reportDao.findAll();
        return reports.stream()
                .map(report -> new ReportPreviewDto(
                        report.getReportId(),
                        report.getReporter().getNickname(),
                        report.getReportReason(),
                        report.getTopic().name(),
                        report.getCreatedTime().toString(),
                        report.isSolved()
                ))
                .toList();
    }

    public ReportDetailDto findReport(Long reportId) {
        ReportEntity report = reportDao.findById(reportId);
        return new ReportDetailDto(
                report.getReportId(),
                report.getReporter().getNickname(),
                report.getReportReason(),
                report.getTopic().name(),
                switch (report.getTopic()) {
                    case USER -> report.getReportedUser().getNickname();
                    case GROUP -> report.getReportedGroupPost().getGroupPostId().toString();
                    case REPLY -> report.getReportedReply().getCommentDetails();
                    case QUESTION -> report.getReportedQuestion().getQuestionDetails();
                },
                report.getCreatedTime().toString(),
                report.isSolved()
        );
    }

    @Transactional
    public void processReport(Long reportId) {
        ReportEntity report = reportDao.findById(reportId);
        processReport(report);
    }

    @Transactional
    public void processReport(ReportEntity report) {
        report.solveReport();
    }

    @Transactional
    protected void sendNotification(Long reporterId, String body) {
        fcmService.sendNotificationByToken(
                FCMNotificationRequestDto.builder()
                        .targetUserId(reporterId)
                        .title("신고")
                        .body(body)
                        .build()
        );
    }

    private ReportEntity createReportEntity(CreateReportRequest request, Long reporterId) {
        UserEntity reporter = userDao.findById(reporterId);
        ReportCategoryEntity reportCategory = reportDao.findReportCategoryById(request.reportCategoryId());
        switch (request.reportTopic()) {
            case USER -> {
                UserEntity reportedUser = userDao.findById(request.targetId());
                return ReportEntity.forUser()
                        .reporter(reporter)
                        .reportedUser(reportedUser)
                        .reportCategory(reportCategory)
                        .reportReason(request.reportReason())
                        .build();
            }
            case GROUP -> {
                GroupPostEntity reportedGroupPost = groupPostDao.findById(request.targetId());
                return ReportEntity.forGroupPost()
                        .reporter(reporter)
                        .reportedGroupPost(reportedGroupPost)
                        .reportCategory(reportCategory)
                        .reportReason(request.reportReason())
                        .build();
            }
            case REPLY -> {
                QuestionCommentEntity reportedReply = questionBoardDao.findCommentById(request.targetId());
                return ReportEntity.forReply()
                        .reporter(reporter)
                        .reportedReply(reportedReply)
                        .reportCategory(reportCategory)
                        .reportReason(request.reportReason())
                        .build();
            }
            case QUESTION -> {
                QuestionBoardEntity reportedQuestion = questionBoardDao.findQuestionById(request.targetId());
                return ReportEntity.forQuestion()
                        .reporter(reporter)
                        .reportedQuestion(reportedQuestion)
                        .reportCategory(reportCategory)
                        .reportReason(request.reportReason())
                        .build();
            }
            default -> throw new NotFoundException();
        }
    }
}
