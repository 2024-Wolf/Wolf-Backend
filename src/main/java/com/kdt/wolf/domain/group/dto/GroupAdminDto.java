package com.kdt.wolf.domain.group.dto;

import java.util.List;

public class GroupAdminDto {
    public record GroupPreviewResponse(
            Long id,
            String name,
            String type,
            String startDate,
            String endDate,
            int memberCount,
            char challengeStatus
    ) { }
    public record GroupPreviewPageResponse(
            List<GroupPreviewResponse> groups,
            int page,
            int size,
            int totalPage
    ) { }
    public record GroupDetailResponse(
            Long id,
            String status,
            String startDate,
            String endDate,
            String type,
            int ChallengeCount,
            String name,
            String GroupLeader,
            int memberCount,
            String groupMembers,
            String tag
    ) { }
}
