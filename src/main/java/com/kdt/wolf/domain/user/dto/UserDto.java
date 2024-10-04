package com.kdt.wolf.domain.user.dto;

public class UserDto {
    public record UserProfileResponse(
            Long id,
            String nickname,
            String profilePicture,
            ActivityMetric activityMetric
    ) {}

    public record UserProfileDetailResponse(
            Long id,
            String nickname,
            String name,
            String email,
            String profilePicture,
            ActivityMetric activityMetric,
            String jobTitle,
            String organization,
            int experience,
            String interests,
            String refundAccount,
            String introduction
    ) {}

    public record UserUpdateRequest(
            Long id,
            String nickname,
            String name,
            String jobTitle,
            String organization,
            int experience,
            String interests,
            String refundAccount,
            String introduction
    ) {}

    public record ActivityMetric(
            int totalStudyParticipation,
            int memberExperienceCount,
            int leaderExperienceCount,
            int challengeSuccessCount,
            int activityRatingGood,
            int activityRatingSoso,
            int activityRatingBad
    ) {}
}
