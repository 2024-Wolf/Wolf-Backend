package com.kdt.wolf.domain.report.service;

import com.kdt.wolf.domain.group.dao.GroupMemberDao;
import com.kdt.wolf.domain.group.dao.GroupPostDao;
import com.kdt.wolf.domain.group.dao.QuestionBoardDao;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.QuestionBoardEntity;
import com.kdt.wolf.domain.group.entity.QuestionCommentEntity;
import com.kdt.wolf.domain.report.dao.ReportProcessDao;
import com.kdt.wolf.domain.report.dto.ReportAdminDto.ReportDetailDto;
import com.kdt.wolf.domain.report.dto.ReportAdminDto.ReportPreviewDto;
import com.kdt.wolf.domain.report.dao.ReportDao;
import com.kdt.wolf.domain.report.dto.ReportDto.CreateReportRequest;
import com.kdt.wolf.domain.report.entity.ReportCategoryEntity;
import com.kdt.wolf.domain.report.entity.ReportEntity;
import com.kdt.wolf.domain.report.entity.ReportProcessEntity;
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
@Transactional
public class ReportService {
    private final UserDao userDao;
    private final ReportDao reportDao;
    private final GroupPostDao groupPostDao;
    private final GroupMemberDao groupMemberDao;
    private final QuestionBoardDao questionBoardDao;
    private final FcmService fcmService;
    private final ReportProcessDao reportProcessDao;

    /* 신고 경고, 정지, 영구정지 처리 통합 메서드 */
    public Long processReport(Long reportId, ReportAction action, String processContent) {
        ReportEntity report = getReport(reportId);
        if(action == ReportAction.NOTHING) {
            processReport(report);
            return report.getReportId();
        }
        switch (report.getTopic()) {
            case USER -> handleUserAction(report, action);
            case GROUP -> handleGroupAction(report, action);
            case REPLY -> handleReplyAction(report, action);
            case QUESTION -> handleQuestionAction(report, action);
        }

        processReport(report);
        saveReportProcess(report, action.name() +" / "+  processContent);
        return report.getReportId();
    }

    private void saveReportProcess(ReportEntity report, String processContent) {
        ReportProcessEntity processEntity = ReportProcessEntity.builder()
                .report(report)
                .reportProcessContent(processContent)
                .build();
        reportProcessDao.save(processEntity);
    }

    /* 사용자에 대한 신고 처리 */
    private void handleUserAction(ReportEntity report, ReportAction action) {
        switch (action) {
            case WARNING -> sendNotification(report.getReportedUser().getUserId(), "신고를 받아 경고를 받았습니다.");
            case SUSPEND -> {
                sendNotification(report.getReportedUser().getUserId(), "신고를 받아 3일 정지되었습니다.");
                userDao.suspendUser(report.getReportedUser().getUserId());
            }
            case BAN -> {
                sendNotification(report.getReportedUser().getUserId(), "신고를 받아 영구 정지되었습니다.");
                userDao.banUser(report.getReportedUser());
            }
            default -> throw new NotFoundException();
        }
    }

    /* 그룹에 대한 신고 처리 */
    private void handleGroupAction(ReportEntity report, ReportAction action) {
        groupMemberDao.findAllByGroupId(report.getReportedGroupPost().getGroupPostId())
                .forEach(groupMember -> sendNotification(groupMember.getUser().getUserId(), "신고를 받아 그룹이 삭제되었습니다."));
        applyUserWarningAction(report.getReportedGroupPost().getLeaderUser(), action);
        groupPostDao.delete(report.getReportedGroupPost());
    }

    /* 댓글에 대한 신고 처리 */
    private void handleReplyAction(ReportEntity report, ReportAction action) {
        sendNotification(report.getReportedReply().getAuthor().getUserId(), "신고를 받아 댓글이 삭제되었습니다.");
        report.getReportedReply().updateCommentDetail("삭제된 댓글입니다.");
        applyUserWarningAction(report.getReportedReply().getAuthor(), action);
    }

    /* 질문에 대한 신고 처리 */
    private void handleQuestionAction(ReportEntity report, ReportAction action) {
        sendNotification(report.getReportedQuestion().getUser().getUserId(), "신고를 받아 질문이 삭제되었습니다.");
        report.getReportedQuestion().updateQuestionDetail("삭제된 질문입니다.");
        applyUserWarningAction(report.getReportedQuestion().getUser(), action);
    }

    private void applyUserWarningAction(UserEntity user, ReportAction action) {
        if (action == ReportAction.SUSPEND) {
            userDao.suspendUser(user.getUserId());
        } else if (action == ReportAction.BAN) {
            userDao.banUser(user);
        }
    }

    private ReportEntity getReport(Long reportId) {
        return reportDao.findById(reportId);
    }

    private void processReport(ReportEntity report) {
        report.solveReport();
    }

    /* 알림 전송 */
    @Transactional
    protected void sendNotification(Long userId, String body) {
        fcmService.sendNotificationByToken(
                FCMNotificationRequestDto.builder()
                        .targetUserId(userId)
                        .title("신고")
                        .body(body)
                        .build()
        );
    }

    /* 신고 생성 */
    public Long createReport(CreateReportRequest request, Long reporterId) {
        ReportEntity report = createReportEntity(request, reporterId);
        return reportDao.save(report).getReportId();
    }

    private ReportEntity createReportEntity(CreateReportRequest request, Long reporterId) {
        UserEntity reporter = userDao.findById(reporterId);
        ReportCategoryEntity reportCategory = reportDao.findReportCategoryById(request.reportCategoryId());

        return switch (request.reportTopic()) {
            case USER -> createUserReport(request, reporter, reportCategory);
            case GROUP -> createGroupReport(request, reporter, reportCategory);
            case REPLY -> createReplyReport(request, reporter, reportCategory);
            case QUESTION -> createQuestionReport(request, reporter, reportCategory);
        };
    }

    private ReportEntity createUserReport(CreateReportRequest request, UserEntity reporter, ReportCategoryEntity reportCategory) {
        UserEntity reportedUser = userDao.findById(request.targetId());
        return ReportEntity.forUser()
                .reporter(reporter)
                .reportedUser(reportedUser)
                .reportCategory(reportCategory)
                .reportReason(request.reportReason())
                .build();
    }

    private ReportEntity createGroupReport(CreateReportRequest request, UserEntity reporter, ReportCategoryEntity reportCategory) {
        GroupPostEntity reportedGroupPost = groupPostDao.findById(request.targetId());
        return ReportEntity.forGroupPost()
                .reporter(reporter)
                .reportedGroupPost(reportedGroupPost)
                .reportCategory(reportCategory)
                .reportReason(request.reportReason())
                .build();
    }

    private ReportEntity createReplyReport(CreateReportRequest request, UserEntity reporter, ReportCategoryEntity reportCategory) {
        QuestionCommentEntity reportedReply = questionBoardDao.findCommentById(request.targetId());
        return ReportEntity.forReply()
                .reporter(reporter)
                .reportedReply(reportedReply)
                .reportCategory(reportCategory)
                .reportReason(request.reportReason())
                .build();
    }

    private ReportEntity createQuestionReport(CreateReportRequest request, UserEntity reporter, ReportCategoryEntity reportCategory) {
        QuestionBoardEntity reportedQuestion = questionBoardDao.findQuestionById(request.targetId());
        return ReportEntity.forQuestion()
                .reporter(reporter)
                .reportedQuestion(reportedQuestion)
                .reportCategory(reportCategory)
                .reportReason(request.reportReason())
                .build();
    }

    public ReportDetailDto findReport(Long reportId) {
        ReportEntity report = reportDao.findById(reportId);
        return new ReportDetailDto(
                report.getReportId(),
                report.getReporter().getNickname(),
                report.getReportReason(),
                report.getReportCategory().getReportCategoryContent(),
                report.getTopic().name(),

                switch (report.getTopic()) {
                    case USER -> report.getReportedUser().getNickname();
                    case GROUP -> report.getReportedGroupPost().getGroupPostId().toString();
                    case REPLY -> report.getReportedReply().getCommentDetails();
                    case QUESTION -> report.getReportedQuestion().getQuestionDetails();
                },
                report.getCreatedTime().toLocalDate().toString(),
                report.isSolved()
        );
    }

    public List<ReportPreviewDto> findAllReports() {
        List<ReportEntity> reports = reportDao.findAll();
        return reports.stream()
                .map(report -> new ReportPreviewDto(
                        report.getReportId(),
                        report.getReporter().getNickname(),
                        report.getReportReason(),
                        report.getTopic().name(),
                        report.getCreatedTime().toLocalDate().toString(),
                        report.isSolved()
                ))
                .toList();
    }
}
