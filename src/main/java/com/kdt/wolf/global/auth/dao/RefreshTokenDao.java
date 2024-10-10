package com.kdt.wolf.global.auth.dao;

import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.global.auth.dto.UserRoleType;
import com.kdt.wolf.global.auth.entity.RefreshTokenEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.auth.repository.RefreshTokenRepository;
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

    public void saveRefreshToken(AdminEntity admin, String refreshToken) {
        RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.createOf(admin, refreshToken);
        refreshTokenRepository.save(refreshTokenEntity);
    }

    public void deleteRefreshTokenByUserId(long userId) {
        Optional<RefreshTokenEntity> refreshTokenEntity = refreshTokenRepository.findByUserId(userId);
        refreshTokenEntity.ifPresent(refreshTokenRepository::delete);
    }

    public void deleteRefreshTokenByAdminId(long adminId) {
        Optional<RefreshTokenEntity> refreshTokenEntity = refreshTokenRepository.findByAdminId(adminId);
        refreshTokenEntity.ifPresent(refreshTokenRepository::delete);
    }

    public void deleteRefreshToken(String refreshToken) {
        Optional<RefreshTokenEntity> refreshTokenEntity = refreshTokenRepository.findByRefreshToken(refreshToken);
        refreshTokenEntity.ifPresent(refreshTokenRepository::delete);
    }
}
