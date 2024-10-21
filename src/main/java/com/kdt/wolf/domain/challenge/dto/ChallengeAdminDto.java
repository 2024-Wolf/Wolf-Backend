package com.kdt.wolf.domain.challenge.dto;

import java.util.List;

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
    public record ChallengePreviewByGroup(
            Long challengePostId,
            String challengeName,
            List<ChallengeParticipantMember> members
    ) {}

    public record ChallengeParticipantMember(
            Long userId,
            String nickname,
            char status,
            char isPayment
    ) { }
}
