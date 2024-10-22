package com.kdt.wolf.domain.user.dto;

import com.kdt.wolf.domain.user.dto.UserDto.ActivityMetric;
import com.kdt.wolf.global.dto.PageResponse;
import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

public class UserAdminDto {
    public record UserPreviewResponse(
            Long id,
            String nickname,
            String jobTitle,
            String organization,
            int experience,
            String joinDate
    ) { }
    public record UserPageResponse(
            List<UserPreviewResponse> userPreviewResponses,
            PageResponse page
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
