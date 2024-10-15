package com.kdt.wolf.domain.link.dto;

import com.kdt.wolf.domain.link.entity.ExternalLinksEntity;

public class LinkResponse {
    private final String linkType;
    private final String linkUrl;

    public LinkResponse(ExternalLinksEntity externalLinksEntity) {
        this.linkType = externalLinksEntity.getLinkType().toString().toLowerCase();
        this.linkUrl = externalLinksEntity.getLinkUrl();
    }

}
