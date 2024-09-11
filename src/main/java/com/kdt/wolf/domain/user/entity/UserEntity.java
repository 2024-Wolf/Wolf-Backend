package com.kdt.wolf.domain.user.entity;

import com.kdt.wolf.domain.user.entity.common.RoleType;
import com.kdt.wolf.domain.user.entity.common.Status;
import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity @Table(name="tb_user")
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
    private RoleType userRole;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public UserEntity(String nickname, String name, String email, String profilePicture) {
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.profilePicture = profilePicture;
        this.userRole = RoleType.MEMBER;
        this.status = Status.ACTIVE;
    }

    public UserEntity(String nickname, String name, String email, String profilePicture, String jobTitle,
                      String organization, int experience, String interests, String refundAccount,
                      String introduction) {
        this.nickname = nickname;
        this.name = name;
        this.email = email;
        this.profilePicture = profilePicture;
        this.jobTitle = jobTitle;
        this.organization = organization;
        this.experience = experience;
        this.interests = interests;
        this.refundAccount = refundAccount;
        this.introduction = introduction;
        this.userRole = RoleType.MEMBER;
        this.status = Status.ACTIVE;
    }

    public static UserEntity makeTestUser() {
        return new UserEntity(
                "test12",
                "test",
                "test12",
                "test",
                "test",
                "test",
                1,
                "test",
                "test",
                "test"
        );
    }
}
