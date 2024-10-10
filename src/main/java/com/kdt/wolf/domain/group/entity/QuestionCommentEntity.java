package com.kdt.wolf.domain.group.entity;

import com.kdt.wolf.domain.group.dto.request.QuestionCommentRequest;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "question_comment")
public class QuestionCommentEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_comment_comment_id")
    @SequenceGenerator(name = "seq_comment_comment_id", sequenceName = "comment_sequence", allocationSize = 1)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionBoardEntity question;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private QuestionCommentEntity parentComment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity author;

    private LocalDateTime createTime;
    private String commentDetails;
    private String commentImageUrl;

    @Builder
    public QuestionCommentEntity(QuestionBoardEntity question, QuestionCommentEntity parentComment,
                                 UserEntity author, LocalDateTime createTime, String commentDetails,
                                 String commentImageUrl) {
        this.question = question;
        this.parentComment = parentComment;
        this.author = author;
        this.createTime = createTime;
        this.commentDetails = commentDetails;
        this.commentImageUrl = commentImageUrl;
    }

    public void updateComment(QuestionCommentRequest request) {
        this.createTime = request.getCommentTime();
        this.commentDetails = request.getCommentDetails();
        this.commentImageUrl = request.getCommentImageUrl();
    }
}
