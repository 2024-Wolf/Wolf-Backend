package com.kdt.wolf.domain.group.dto;

import com.kdt.wolf.domain.group.entity.GroupPostEntity;
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
    ) {
        public GroupPreviewResponse(GroupPostEntity entity, Long memberCount) {
            this(
                    entity.getGroupPostId(),
                    entity.getName(),
                    entity.getType().name(),
                    entity.getStartDate().toString(),
                    entity.getEndDate().toString(),
                    memberCount.intValue(),
                    entity.getChallengeStatus()
            );
        }
    }
    public record GroupPreviewPageResponse(
            List<GroupPreviewResponse> groups,
            int page,
            int size,
            int totalPage
    ) { }
}
