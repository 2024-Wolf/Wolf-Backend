package com.kdt.wolf.domain.user.entity;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLRestriction;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Table(
        name = "refresh_token",
        indexes = {@Index(name = "IDX_REFRESH_TOKEN_VALUE", columnList = "refresh_token_value")})
@Entity
@SQLRestriction("expired_datetime > now()")
@DynamicUpdate
public class RefreshTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_token_id", columnDefinition = "bigint")
    private final Long id = 0L;

    @Comment("토큰 소유자 유저 아이디")
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Comment("리프레시 토큰값")
    @Column(name = "refresh_token_value", length = 255, nullable = false)
    private String refreshTokenValue;

    @Comment("발급일시")
    @Column(name = "issued_datetime")
    private Instant issuedDatetime;

    public static RefreshTokenEntity createOf(
            UserEntity user,
            String refreshTokenValue) {
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        refreshToken.user = user;
        refreshToken.refreshTokenValue = refreshTokenValue;
        refreshToken.issuedDatetime = Instant.now();

        return refreshToken;
    }
}
