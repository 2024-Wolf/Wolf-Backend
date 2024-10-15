package com.kdt.wolf.domain.group.dto.response;

import com.kdt.wolf.domain.group.entity.ExternalLinksEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;

public class LinkResponse {
    private final String linkType;
    private final String linkUrl;

    public LinkResponse(ExternalLinksEntity externalLinksEntity) {
        this.linkType = externalLinksEntity.getLinkType().toString().toLowerCase();
        this.linkUrl = externalLinksEntity.getLinkUrl();
    }

}
