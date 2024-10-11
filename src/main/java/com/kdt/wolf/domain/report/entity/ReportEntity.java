package com.kdt.wolf.domain.report.entity;

import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.QuestionBoardEntity;
import com.kdt.wolf.domain.group.entity.QuestionCommentEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "report")
public class ReportEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_report_id")
    @SequenceGenerator(name = "seq_report_id", sequenceName = "report_sequence", allocationSize = 1)
    private Long reportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repoter_user_id", nullable = false)
    private UserEntity reporter;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportTopic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reported_user_id")
    private UserEntity reportedUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_post_id")
    GroupPostEntity reportedGroupPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reply_id")
    private QuestionCommentEntity reportedReply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionBoardEntity reportedQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private ReportCategoryEntity reportCategory;

    @Column(length = 1000)
    private String reportReason;

    @Column(nullable = false, columnDefinition = "NUMBER(1) DEFAULT 0 CHECK (is_solved IN (0, 1))")
    private boolean isSolved = false;

    // Report for a User
    @Builder(builderMethodName = "forUser")
    public ReportEntity(UserEntity reporter, UserEntity reportedUser, ReportCategoryEntity reportCategory, String reportReason) {
        this.reporter = reporter;
        this.reportedUser = reportedUser;
        this.reportCategory = reportCategory;
        this.reportReason = reportReason;
        this.topic = ReportTopic.USER;
    }

    // Report for a Group Post
    @Builder(builderMethodName = "forGroupPost")
    public ReportEntity(UserEntity reporter, GroupPostEntity reportedGroupPost, ReportCategoryEntity reportCategory, String reportReason) {
        this.reporter = reporter;
        this.reportedGroupPost = reportedGroupPost;
        this.reportCategory = reportCategory;
        this.reportReason = reportReason;
        this.topic = ReportTopic.GROUP;
    }

    // Report for a Reply
    @Builder(builderMethodName = "forReply")
    public ReportEntity(UserEntity reporter, QuestionCommentEntity reportedReply, ReportCategoryEntity reportCategory, String reportReason) {
        this.reporter = reporter;
        this.reportedReply = reportedReply;
        this.reportCategory = reportCategory;
        this.reportReason = reportReason;
        this.topic = ReportTopic.REPLY;
    }

    // Report for a Question
    @Builder(builderMethodName = "forQuestion")
    public ReportEntity(UserEntity reporter, QuestionBoardEntity reportedQuestion, ReportCategoryEntity reportCategory, String reportReason) {
        this.reporter = reporter;
        this.reportedQuestion = reportedQuestion;
        this.reportCategory = reportCategory;
        this.reportReason = reportReason;
        this.topic = ReportTopic.QUESTION;
    }
}
