package com.kdt.wolf.domain.group.entity;

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

    private String commentDetails;
    private String commentImageUrl;

    @Builder
    public QuestionCommentEntity(QuestionBoardEntity question, QuestionCommentEntity parentComment,
                                 UserEntity author, String commentDetails,
                                 String commentImageUrl) {
        this.question = question;
        this.parentComment = parentComment;
        this.author = author;
        this.commentDetails = commentDetails;
        this.commentImageUrl = commentImageUrl;
    }
}
