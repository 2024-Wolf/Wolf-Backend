package com.kdt.wolf.domain.group.dto.response;

import com.kdt.wolf.domain.group.entity.ExternalLinksEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;

public class LinkResponse {
    private final User user;
    private final String linkType;
    private final String linkUrl;

    public LinkResponse(ExternalLinksEntity externalLinksEntity) {
        this.user = new User(externalLinksEntity.getUser());
        this.linkType = externalLinksEntity.getLinkType().toString().toLowerCase();
        this.linkUrl = externalLinksEntity.getLinkUrl();
    }

    public class User {
        private final Long userId;
        private final String userNickname;
        private final String userProfileImg;

        public User(UserEntity user) {
            this.userId = user.getUserId();
            this.userNickname = user.getNickname();
            this.userProfileImg = user.getProfilePicture();
        }
    }
}
