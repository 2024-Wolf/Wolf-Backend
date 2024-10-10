package com.kdt.wolf.domain.report.entity;

import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.QuestionBoardEntity;
import com.kdt.wolf.domain.group.entity.QuestionCommentEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    private Long replyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private ReportCategoryEntity reportCategory;

    @Column(length = 1000)
    private String reportReason;

    @Column(nullable = false, columnDefinition = "NUMBER(1) DEFAULT 0 CHECK (is_solved IN (0, 1))")
    private boolean isSolved = false;


}
