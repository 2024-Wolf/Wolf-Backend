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
    @Column(name = "refresh_token_id")
    private final Long id = 0L;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "refresh_token_value", length = 255, nullable = false)
    private String refreshTokenValue;

    @Column(name = "issued_datetime")
    private LocalDateTime issuedDatetime;

    public static RefreshTokenEntity createOf(
            UserEntity user,
            String refreshTokenValue) {
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        refreshToken.user = user;
        refreshToken.refreshTokenValue = refreshTokenValue;
        refreshToken.issuedDatetime = LocalDateTime.now();

        return refreshToken;
    }
}
