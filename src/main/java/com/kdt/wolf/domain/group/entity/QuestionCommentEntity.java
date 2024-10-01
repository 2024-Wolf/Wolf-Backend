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
    @JoinColumn(referencedColumnName = "question_id", nullable = false)
    private QuestionBoardEntity questionId;

    @ManyToOne
    @JoinColumn(referencedColumnName = "comment_id")
    private QuestionCommentEntity parentCommentId;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", nullable = false)
    private UserEntity authorId;

    private String commentDetails;
    private String commentImageUrl;

    @Builder
    public QuestionCommentEntity(QuestionBoardEntity questionId, QuestionCommentEntity parentCommentId,
                                 UserEntity authorId, String commentDetails,
                                 String commentImageUrl) {
        this.questionId = questionId;
        this.parentCommentId = parentCommentId;
        this.authorId = authorId;
        this.commentDetails = commentDetails;
        this.commentImageUrl = commentImageUrl;
    }
}
