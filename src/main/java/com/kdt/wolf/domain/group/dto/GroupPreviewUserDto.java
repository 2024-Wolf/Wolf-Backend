package com.kdt.wolf.domain.group.dto;

import com.kdt.wolf.global.dto.PageResponse;
import java.util.List;

public class GroupPreviewUserDto{
    public record GroupPreviewUser(
            long groupPostId,
            String thumbnail,
            String name,
            String tag,
            String type,
            String endDate,
            char isContainChallenge,
            String participationDate
    ) { }

    public record GroupPreviewUserPageResponse (
            List<GroupPreviewUser> groupPostPreviews,
            PageResponse page
    ) { }
}
