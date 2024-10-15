package com.kdt.wolf.domain.group.dto.request;

import com.kdt.wolf.domain.user.entity.UserEntity;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class QuestionCommentRequest {
    private final String commentDetails;
    private final String commentImageUrl;
    private final LocalDateTime commentTime;

    public QuestionCommentRequest(UserEntity user, String commentDetails, String commentImageUrl, LocalDateTime commentTime) {
        this.commentDetails = commentDetails;
        this.commentImageUrl = commentImageUrl;
        this.commentTime = commentTime;
    }
}

