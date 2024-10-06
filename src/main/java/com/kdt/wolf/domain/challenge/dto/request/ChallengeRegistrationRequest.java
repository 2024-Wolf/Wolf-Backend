package com.kdt.wolf.domain.challenge.dto.request;

import lombok.Getter;

import javax.annotation.Nullable;

@Getter
public class ChallengeRegistrationRequest {
    private Long challengePostId;
    private Long groupPostId;

    @Nullable
    private String challengeAmount;
}
