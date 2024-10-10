package com.kdt.wolf.domain.challenge.dto.request;

import lombok.Getter;

@Getter
public class ChallengePaymentRequest {
    private Long challengePostId;
    private Long groupPostId;
    private Long amount;
    private String payStatus;
}
