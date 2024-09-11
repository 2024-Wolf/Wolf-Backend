package com.kdt.wolf.domain.user.dto.response;

import com.kdt.wolf.domain.user.entity.UserEntity;
import lombok.Getter;

@Getter
public class UserProfileResponse {
    private final Long userId;
    private final String nickname;
    private final String name;
    private final String email;
    private final String profile_picture;

    private final String job_title;
    private final String organization;
    private final int experience;
    private final String interests;
    private final String refundAccount;
    private final String introduction;

    public UserProfileResponse(UserEntity user) {
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
        this.name = user.getName();
        this.email = user.getEmail();
        this.profile_picture = user.getProfilePicture();
        this.job_title = user.getJobTitle();
        this.organization = user.getOrganization();
        this.experience = user.getExperience();
        this.interests = user.getInterests();
        this.refundAccount = user.getRefundAccount();
        this.introduction = user.getIntroduction();
    }
}
