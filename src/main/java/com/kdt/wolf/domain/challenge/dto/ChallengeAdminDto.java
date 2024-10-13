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

    public record VerificationDetail(
            Long id,
            String userNickname,
            String challengeTitle,
            String certificationNo,
            String institutionName,
            String verificationContent,
            String createdAt,
            boolean isVerified
    ) { }
}
