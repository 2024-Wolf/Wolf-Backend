package com.kdt.wolf.domain.report.service;

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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReportService {
    private final UserDao userDao;
    private final ReportDao reportDao;
    private final GroupPostDao groupPostDao;
    private final QuestionBoardDao questionBoardDao;

    public Long createReport(CreateReportRequest request, Long reporterId) {
        UserEntity reporter = userDao.findById(reporterId);
        ReportCategoryEntity reportCategory = reportDao.findReportCategoryById(request.reportCategoryId());

        ReportEntity report = switch (request.reportTopic()) {
            case USER -> {
                UserEntity reportedUser = userDao.findById(request.targetId());
                yield ReportEntity.forUser()
                        .reporter(reporter)
                        .reportedUser(reportedUser)
                        .reportCategory(reportCategory)
                        .reportReason(request.reportReason())
                        .build();
            }
            case GROUP -> {
                GroupPostEntity reportedGroupPost = groupPostDao.findById(request.targetId());
                yield ReportEntity.forGroupPost()
                        .reporter(reporter)
                        .reportedGroupPost(reportedGroupPost)
                        .reportCategory(reportCategory)
                        .reportReason(request.reportReason())
                        .build();
            }
            case REPLY -> {
                QuestionCommentEntity reportedReply = questionBoardDao.findCommentById(request.targetId());
                yield ReportEntity.forReply()
                        .reporter(reporter)
                        .reportedReply(reportedReply)
                        .reportCategory(reportCategory)
                        .reportReason(request.reportReason())
                        .build();
            }
            case QUESTION -> {
                QuestionBoardEntity reportedQuestion = questionBoardDao.findQuestionById(request.targetId());
                yield ReportEntity.forQuestion()
                        .reporter(reporter)
                        .reportedQuestion(reportedQuestion)
                        .reportCategory(reportCategory)
                        .reportReason(request.reportReason())
                        .build();
            }
        };

        return reportDao.save(report).getReportId();
    }
}
