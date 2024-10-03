package com.kdt.wolf.domain.user.dao;

import com.kdt.wolf.domain.user.entity.RefreshTokenEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.RefreshTokenRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RefreshTokenDao {
    private final RefreshTokenRepository refreshTokenRepository;

    public void saveRefreshToken(UserEntity user, String refreshToken) {
        RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.createOf(user, refreshToken);
        refreshTokenRepository.save(refreshTokenEntity);
    }

    public void deleteRefreshTokenByUserId(long userId) {
        Optional<RefreshTokenEntity> refreshTokenEntity = refreshTokenRepository.findByUserId(userId);
        if(refreshTokenRepository.findByUserId(userId).isEmpty()) {
            return;
        }
        refreshTokenRepository.delete(refreshTokenEntity.get());
    }

    public void deleteRefreshToken(String refreshToken) {
        Optional<RefreshTokenEntity> refreshTokenEntity = refreshTokenRepository.findByRefreshToken(refreshToken);
        if(refreshTokenRepository.findByRefreshToken(refreshToken).isEmpty()) {
            return;
        }
        refreshTokenRepository.delete(refreshTokenEntity.get());
    }
}
