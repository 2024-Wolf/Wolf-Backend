package com.kdt.wolf.domain.user.dao;

import com.kdt.wolf.domain.user.entity.RefreshTokenEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.RefreshTokenRepository;
import com.kdt.wolf.global.auth.provider.JwtTokenProvider;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshTokenDao {
    private final RefreshTokenRepository refreshTokenRepository;

    public void saveRefreshToken(UserEntity user, String refreshToken) {
        RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.createOf( user, refreshToken);
    }

}
