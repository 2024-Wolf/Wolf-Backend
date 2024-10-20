package com.kdt.wolf.domain.challenge.dto;

public class ChallengeAdminDto {
    public record VerificationPreview(
            Long id,
            String userNickname,
            String title,
            String groupTitle,
            String createdAt,
            String isVerified
    ) { }

    public record VerificationDetail(
            Long id,
            Long challengePostId,
            Long groupId,
            String userNickname,
            String title,
            String certificationNo,
            String institutionName,
            String verificationContent,
            String createdAt,
            String isVerified
    ) { }
}
