package com.kdt.wolf.domain.group.dto.request;

import com.kdt.wolf.domain.user.entity.UserEntity;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class QuestionRequest {
    private final UserEntity user;
    private final String questionDetails;
    private final String questionImageUrl;
    private final LocalDateTime questionTime;

    public QuestionRequest(UserEntity user, String questionDetails, String questionImageUrl, LocalDateTime questionTime) {
        this.user = user;
        this.questionDetails = questionDetails;
        this.questionImageUrl = questionImageUrl;
        this.questionTime = questionTime;
    }
}

