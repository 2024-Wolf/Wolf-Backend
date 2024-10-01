package com.kdt.wolf.domain.group.entity;

import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.group.entity.common.BoardType;
import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "question_board")
public class QuestionBoardEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_question_question_id")
    @SequenceGenerator(name = "seq_question_question_id", sequenceName = "question_sequence", allocationSize = 1)
    private Long questionId;

    @ManyToOne
    @JoinColumn(referencedColumnName = "group_post_id", nullable = false)
    private GroupPostEntity groupPostId;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", nullable = false)
    private UserEntity userId;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Column
    private String questionDetails;

    @Column
    private String questionImageUrl;

    @Builder
    public QuestionBoardEntity(GroupPostEntity groupPostId, UserEntity userId, BoardType boardType,
                               String questionDetails, String questionImageUrl) {
        this.groupPostId = groupPostId;
        this.userId = userId;
        this.boardType = boardType;
        this.questionDetails = questionDetails;
        this.questionImageUrl = boardType == BoardType.COMMUNICATION ? questionImageUrl : null;
    }
}
