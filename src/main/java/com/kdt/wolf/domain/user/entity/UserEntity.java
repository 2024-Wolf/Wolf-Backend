package com.kdt.wolf.domain.user.entity;

import com.kdt.wolf.domain.user.dto.UserDto.UserProfileDetailResponse;
import com.kdt.wolf.domain.user.entity.common.SocialType;
import com.kdt.wolf.domain.user.entity.common.Status;
import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user_user_id")
    @SequenceGenerator(name = "seq_user_user_id", sequenceName = "user_sequence", allocationSize = 1)
    private Long userId;

    @Column(unique = true)
    private String nickname;

    private String name;
    private String email;
    private String profilePicture;

    private String jobTitle;
    private String organization;
    private int experience;
    private String interests;
    private String refundAccount;
    private String introduction;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    private Status status;

    // 정지 날짜를 저장할 필드 (3일 정지 시 사용)
    private LocalDate suspensionDate;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private ActivityMetricsEntity activityMetrics;

    @Builder
    public UserEntity(String nickname, String name, String email, String profilePicture, SocialType socialType,
                      Status status) {
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.profilePicture = profilePicture;
        this.socialType = socialType;
        this.status = status;
    }

    public void updateDetailProfile(String jobTitle, String organization, int experience, String interests) {
        this.jobTitle = jobTitle;
        this.organization = organization;
        this.experience = experience;
        this.interests = interests;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }

    public void changeStatus(Status status) {
        this.status = status;
    }

    public UserProfileDetailResponse toUserProfileDetailResponse() {
        return new UserProfileDetailResponse(
                userId,
                nickname,
                name,
                email,
                profilePicture,
                activityMetrics.toResponse(),
                jobTitle,
                organization,
                experience,
                interests,
                refundAccount,
                introduction
        );
    }
}