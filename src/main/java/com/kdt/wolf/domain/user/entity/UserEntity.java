package com.kdt.wolf.domain.user.entity;

import com.kdt.wolf.domain.user.entity.common.Status;
import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
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
        private Status status;

        // ActivityMetrics와 1:1 관계 설정
        @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
        private ActivityMetricsEntity activityMetrics;

    @Builder
    public UserEntity(String nickname, String name, String email, String profilePicture) {
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.profilePicture = profilePicture;
        this.status = Status.ACTIVE;
    }

    public void updateProfile(String jobTitle, String organization, int experience, String interests, String refundAccount, String introduction) {
        this.jobTitle = jobTitle;
        this.organization = organization;
        this.experience = experience;
        this.interests = interests;
        this.refundAccount = refundAccount;
        this.introduction = introduction;
    }

}
