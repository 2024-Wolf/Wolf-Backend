package com.kdt.wolf.domain.group.dto.response;

import com.kdt.wolf.domain.group.entity.QuestionBoardEntity;
import com.kdt.wolf.domain.group.entity.QuestionCommentEntity;
import com.kdt.wolf.domain.group.entity.common.BoardType;
import com.kdt.wolf.domain.user.entity.UserEntity;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class QuestionResponse {
    private final QuestionAuthor user;
    private final String boardType;
    private final String questionDetails;
    private final String questionImageUrl;
    private final LocalDateTime questionTime;
    private final List<QuestionCommentResponse> comments;

    public QuestionResponse(QuestionBoardEntity question, List<QuestionCommentEntity> comments) {
        this.user = new QuestionAuthor(question.getUser());
        this.boardType = BoardType.QUESTION.equals(question.getBoardType()) ? "question" : "communication";
        this.questionDetails = question.getQuestionDetails();
        this.questionImageUrl = question.getQuestionImageUrl();
        this.questionTime = question.getQuestionTime();
        this.comments = comments.stream()
                .map(QuestionCommentResponse::new)
                .toList();
    }

    public class QuestionAuthor {
        private final Long userId;
        private final String userNickname;
        private final String userProfileImg;

        public QuestionAuthor(UserEntity user) {
            this.userId = user.getUserId();
            this.userNickname = user.getNickname();
            this.userProfileImg = user.getProfilePicture();
        }
    }
}

