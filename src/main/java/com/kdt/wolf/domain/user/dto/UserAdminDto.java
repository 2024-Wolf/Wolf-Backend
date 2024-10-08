package com.kdt.wolf.domain.user.dto;

import com.kdt.wolf.domain.user.dto.UserDto.ActivityMetric;
import lombok.Builder;

public class UserAdminDto {
    public record UserPreviewResponse(
            Long id,
            String nickname,
            String jobTitle,
            String organization,
            int experience,
            String joinDate
    ) { }
    @Builder
    public record UserDetailResponse(
            Long id,
            String nickname,
            String name,
            String email,
            String profilePicture,
            String jobTitle,
            String organization,
            int experience,
            String interests,
            String refundAccount,
            String introduction,
            String socialType,

            String status,
            String suspensionDate,

            String joinDate,
            ActivityMetric activityMetrics
    ) { }
}
