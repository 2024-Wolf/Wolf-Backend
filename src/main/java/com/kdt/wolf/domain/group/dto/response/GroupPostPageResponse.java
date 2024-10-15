package com.kdt.wolf.domain.group.dto.response;

import com.kdt.wolf.global.dto.PageResponse;
import java.util.List;

public record GroupPostPageResponse (
        List<GroupPostResponse> groupPostResponseList,
        PageResponse page
) { }
