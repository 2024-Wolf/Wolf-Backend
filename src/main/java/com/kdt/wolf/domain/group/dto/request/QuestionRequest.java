package com.kdt.wolf.domain.group.dto.request;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class QuestionRequest {
    private final String questionDetails;
    private final String questionImageUrl;
    private final LocalDateTime questionTime;

    public QuestionRequest(String questionDetails, String questionImageUrl, LocalDateTime questionTime) {
        this.questionDetails = questionDetails;
        this.questionImageUrl = questionImageUrl;
        this.questionTime = questionTime;
    }
}

