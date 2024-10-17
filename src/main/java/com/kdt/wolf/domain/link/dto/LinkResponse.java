package com.kdt.wolf.domain.link.dto;

import com.kdt.wolf.domain.link.entity.ExternalLinksEntity;

public record LinkResponse(
        Long linkId,
        String linkType,
        String linkUrl) {
    public LinkResponse(ExternalLinksEntity externalLinksEntity) {
        this(
                externalLinksEntity.getLinkId(),
                externalLinksEntity.getLinkType().toString().toLowerCase(),
                externalLinksEntity.getLinkUrl()
        );
    }
}
