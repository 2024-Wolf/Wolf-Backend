package com.kdt.wolf.domain.user.info;


import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.entity.common.SocialType;
import com.kdt.wolf.domain.user.entity.common.Status;

public abstract class OAuth2UserInfo {
    public abstract String getId();

    public abstract String getName();

    public abstract String getNickname();

    public abstract String getEmail();

    public abstract String getImageUrl();

    public abstract SocialType getSocialType();


    public UserEntity toEntity() {
        return UserEntity.builder()
                .name(getName())
                .nickname(getNickname())
                .email(getEmail())
                .profilePicture(getImageUrl())
                .socialType(getSocialType())
                .status(Status.ACTIVE)
                .build();
    }
}
