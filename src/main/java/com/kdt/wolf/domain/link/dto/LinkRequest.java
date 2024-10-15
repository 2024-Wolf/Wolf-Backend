package com.kdt.wolf.domain.link.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LinkRequest {
    private String linkType;
    private String linkUrl;
}
