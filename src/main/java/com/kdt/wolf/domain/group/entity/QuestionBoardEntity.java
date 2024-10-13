package com.kdt.wolf.domain.group.entity;

import com.kdt.wolf.domain.group.dto.request.QuestionRequest;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.group.entity.common.BoardType;
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
@Table(name = "question_board")
public class QuestionBoardEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_question_question_id")
    @SequenceGenerator(name = "seq_question_question_id", sequenceName = "question_sequence", allocationSize = 1)
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "group_post_id", nullable = false)
    private GroupPostEntity groupPost;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @Column
    private LocalDateTime questionTime;

    @Column
    private String questionDetails;

    @Column
    private String questionImageUrl;

    @Builder
    public QuestionBoardEntity(GroupPostEntity groupPost, UserEntity user, BoardType boardType, LocalDateTime questionTime,
                               String questionDetails, String questionImageUrl) {
        this.groupPost = groupPost;
        this.user = user;
        this.boardType = boardType;
        this.questionTime = questionTime;
        this.questionDetails = questionDetails;
        this.questionImageUrl = (boardType == BoardType.COMMUNICATION) ? questionImageUrl : null;
    }

    public void updateQuestion(QuestionRequest request) {
        this.questionTime = request.getQuestionTime();
        this.questionDetails = request.getQuestionDetails();
        this.questionImageUrl = request.getQuestionImageUrl();
    }

    public void updateQuestionDetail(String questionDetails) {
        this.questionDetails = questionDetails;
        if(questionImageUrl != null) {
            this.questionImageUrl = null;
        }
    }
}
