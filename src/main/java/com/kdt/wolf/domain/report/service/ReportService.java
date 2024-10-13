package com.kdt.wolf.domain.report.service;

import com.kdt.wolf.domain.group.dao.GroupMemberDao;
import com.kdt.wolf.domain.group.dao.GroupPostDao;
import com.kdt.wolf.domain.group.dao.QuestionBoardDao;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.QuestionBoardEntity;
import com.kdt.wolf.domain.group.entity.QuestionCommentEntity;
import com.kdt.wolf.domain.report.dao.ReportDao;
import com.kdt.wolf.domain.report.dto.ReportDto.CreateReportRequest;
import com.kdt.wolf.domain.report.entity.ReportCategoryEntity;
import com.kdt.wolf.domain.report.entity.ReportEntity;
import com.kdt.wolf.domain.user.dao.UserDao;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.fcm.service.FcmService;
import com.kdt.wolf.global.fcm.service.dto.FCMNotificationRequestDto;
import jakarta.transaction.Transactional;
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

    /* 신고 생성 */
    public Long createReport(CreateReportRequest request, Long reporterId) {
        ReportEntity report = createReportEntity(request, reporterId);
        return reportDao.save(report).getReportId();
    }

    /* 신고 경고 처리 */
    @Transactional
    public Long processWarningReport(Long reportId) {
        ReportEntity report = getReport(reportId);

        switch (report.getTopic()) {
            case USER -> handleUserWarning(report);
            case GROUP -> handleGroupWarning(report);
            case REPLY -> handleReplyWarning(report);
            case QUESTION -> handleQuestionWarning(report);
        }

        processReport(report);
        return report.getReportId();
    }

    /* 신고 3일 정지 처리 */
    @Transactional
    public Long processSuspendReport(Long reportId) {
        ReportEntity report = getReport(reportId);

        switch (report.getTopic()) {
            case USER -> handleUserSuspension(report);
            case GROUP -> handleGroupSuspension(report);
            case REPLY -> handleReplySuspension(report);
            case QUESTION -> handleQuestionSuspension(report);
        }

        processReport(report);
        return report.getReportId();
    }

    private void handleUserWarning(ReportEntity report) {
        sendNotification(report.getReportedUser().getUserId(), "신고를 받아 경고를 받았습니다.");
    }

    private void handleGroupWarning(ReportEntity report) {
        groupMemberDao.findAllByGroupId(report.getReportedGroupPost().getGroupPostId())
                .forEach(groupMember -> sendNotification(groupMember.getUser().getUserId(), "신고를 받아 그룹이 삭제되었습니다."));
        groupPostDao.delete(report.getReportedGroupPost());
    }

    private void handleReplyWarning(ReportEntity report) {
        report.getReportedReply().updateCommentDetail("삭제된 댓글입니다.");
        sendNotification(report.getReportedReply().getAuthor().getUserId(), "신고를 받아 댓글이 삭제되었습니다.");
    }

    private void handleQuestionWarning(ReportEntity report) {
        report.getReportedQuestion().updateQuestionDetail("삭제된 질문입니다.");
        sendNotification(report.getReportedQuestion().getUser().getUserId(), "신고를 받아 질문이 삭제되었습니다.");
    }

    private void handleUserSuspension(ReportEntity report) {
        sendNotification(report.getReportedUser().getUserId(), "신고를 받아 3일 정지 되었습니다.");
        userDao.suspendUser(report.getReportedUser().getUserId());
    }

    private void handleGroupSuspension(ReportEntity report) {
        sendNotification(report.getReportedGroupPost().getLeaderUser().getUserId(), "신고를 받아 3일 정지 되었습니다.");
        userDao.suspendUser(report.getReportedGroupPost().getLeaderUser().getUserId());
        groupPostDao.delete(report.getReportedGroupPost());
    }

    private void handleReplySuspension(ReportEntity report) {
        sendNotification(report.getReportedReply().getAuthor().getUserId(), "신고를 받아 3일 정지 되었습니다.");
        userDao.suspendUser(report.getReportedReply().getAuthor().getUserId());
    }

    private void handleQuestionSuspension(ReportEntity report) {
        sendNotification(report.getReportedQuestion().getUser().getUserId(), "신고를 받아 3일 정지 되었습니다.");
        userDao.suspendUser(report.getReportedQuestion().getUser().getUserId());
        report.getReportedQuestion().updateQuestionDetail("삭제된 질문입니다.");
    }

    private ReportEntity getReport(Long reportId) {
        return reportDao.findById(reportId);
    }

    private ReportEntity createReportEntity(CreateReportRequest request, Long reporterId) {
        UserEntity reporter = userDao.findById(reporterId);
        ReportCategoryEntity reportCategory = reportDao.findReportCategoryById(request.reportCategoryId());

        return switch (request.reportTopic()) {
            case USER -> createUserReport(request, reporter, reportCategory);
            case GROUP -> createGroupReport(request, reporter, reportCategory);
            case REPLY -> createReplyReport(request, reporter, reportCategory);
            case QUESTION -> createQuestionReport(request, reporter, reportCategory);
            default -> throw new NotFoundException();
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

    @Transactional
    public void processReport(Long reportId) {
        ReportEntity report = getReport(reportId);
        processReport(report);
    }

    @Transactional
    public void processReport(ReportEntity report) {
        report.solveReport();
    }

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
}
