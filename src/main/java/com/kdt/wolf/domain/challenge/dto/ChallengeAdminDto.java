package com.kdt.wolf.domain.challenge.dto;

public class ChallengeAdminDto {
    public record VerificationPreview(
            Long id,
            String userNickname,
            String challengeTitle,
            String groupTitle,
            String createdAt,
            boolean isVerified
    ) { }
}
