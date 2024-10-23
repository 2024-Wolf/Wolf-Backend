package com.kdt.wolf.domain.challenge.dto.request;


import lombok.Getter;

public class ChallengeVerificationRequest {
    public record VerificationRequest(
            Long challengePostId,
            Long groupPostId,
            String nickname,
            String status,
            String certificationNo,
            String institutionName,
            String content
    ) {}
}
