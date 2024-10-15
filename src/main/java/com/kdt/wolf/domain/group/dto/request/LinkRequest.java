package com.kdt.wolf.domain.group.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LinkRequest {
    private String linkType;
    private String linkUrl;
}
