package com.kdt.wolf.global.auth.entity;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.auth.dto.UserRoleType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(
        name = "refresh_token",
        indexes = {@Index(name = "IDX_REFRESH_TOKEN_VALUE", columnList = "refresh_token_value")})
@Entity
@DynamicUpdate
public class RefreshTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_refresh_token_id")
    @SequenceGenerator(name = "seq_refresh_token_id", sequenceName = "refresh_token_sequence", allocationSize = 1)
    @Column(name = "refresh_token_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id" , nullable = true)
    private UserEntity user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "admin_id", nullable = true)
    private AdminEntity admin;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role_type", nullable = false)
    private UserRoleType userRoleType;  // ADMIN 또는 USER 구분

    @Column(name = "refresh_token_value", length = 255, nullable = false)
    private String refreshTokenValue;

    @Column(name = "issued_datetime")
    private LocalDateTime issuedDatetime;


    public static RefreshTokenEntity createOf(UserEntity user, String refreshTokenValue) {
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        refreshToken.user = user;
        refreshToken.userRoleType = UserRoleType.USER;
        refreshToken.refreshTokenValue = refreshTokenValue;
        refreshToken.issuedDatetime = LocalDateTime.now();
        return refreshToken;
    }

    public static RefreshTokenEntity createOf(AdminEntity admin, String refreshTokenValue) {
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        refreshToken.admin = admin;
        refreshToken.userRoleType = UserRoleType.ADMIN;
        refreshToken.refreshTokenValue = refreshTokenValue;
        refreshToken.issuedDatetime = LocalDateTime.now();
        return refreshToken;
    }
}
