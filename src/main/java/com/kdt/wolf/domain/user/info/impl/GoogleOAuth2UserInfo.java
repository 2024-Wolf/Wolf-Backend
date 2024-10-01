package com.kdt.wolf.domain.user.info.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.kdt.wolf.domain.user.entity.common.SocialType;
import com.kdt.wolf.domain.user.info.OAuth2UserInfo;
import lombok.Getter;

@Getter
public class GoogleOAuth2UserInfo extends OAuth2UserInfo {
    private final String id;
    private final String name;
    private final String nickname;
    private final String email;
    private final String imageUrl;

    private final SocialType socialType = SocialType.GOOGLE;

    public GoogleOAuth2UserInfo(GoogleIdToken.Payload payload) {
        this.id = payload.getSubject();
        this.nickname = (String) payload.get("nickname"); // TODO : 확인 X
        this.email = payload.getEmail();
        this.name = (String) payload.get("name");
        this.imageUrl = (String) payload.get("picture");
    }
}
