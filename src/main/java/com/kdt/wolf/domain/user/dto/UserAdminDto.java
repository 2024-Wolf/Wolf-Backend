package com.kdt.wolf.domain.user.dto;

import com.kdt.wolf.domain.user.dto.UserDto.ActivityMetric;

public class UserAdminDto {
    public record UserPreviewResponse(
            Long id,
            String nickname,
            String jobTitle,
            String organization,
            int experience,
            String joinDate
    ) { }

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
            //신고 정보 ?
    ) { }
}
