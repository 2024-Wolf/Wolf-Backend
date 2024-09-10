package com.kdt.wolf.domain.user.dto.response;

import com.kdt.wolf.domain.user.entity.UserEntity;
import lombok.Getter;

@Getter
public class UserProfileResponse {
    private Long userId;
    private String nickname;
    private String name;
    private String email;
    private String profile_picture;

    private String job_title;
    private String organization;
    private int experience;
    private String interests;
    private String refundAccount;
    private String introduction;

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
