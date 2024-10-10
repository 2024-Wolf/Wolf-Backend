package com.kdt.wolf.global.auth.service;

import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.global.auth.dao.RefreshTokenDao;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.auth.provider.JwtTokenProvider;
import com.kdt.wolf.global.exception.UnauthorizedException;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final JwtTokenProvider tokenProvider;
    private final RefreshTokenDao refreshTokenDao;

    public void saveRefreshToken(UserEntity user, String refreshToken) {
        if(isRefreshTokenInvalid(refreshToken)) {
            throw new UnauthorizedException();
        }
        refreshTokenDao.saveRefreshToken(user, refreshToken);
    }

    public void saveRefreshToken(AdminEntity admin, String refreshToken) {
        if(isRefreshTokenInvalid(refreshToken)) {
            throw new UnauthorizedException();
        }
        refreshTokenDao.saveRefreshToken(admin, refreshToken);
    }

    private boolean isRefreshTokenInvalid(String refreshToken) {
        tokenProvider.validateToken(refreshToken);
        Date refreshTokenExpireTime = getRefreshTokenExpireTime(refreshToken);
        return refreshTokenExpireTime.before(new Date());
    }

    private Date getRefreshTokenExpireTime(String refreshToken) {
        return tokenProvider.getExpirationDateFromToken(refreshToken);
    }

    public void deleteRefreshTokenByUserId(Long userId) {
        refreshTokenDao.deleteRefreshTokenByUserId(userId);
    }

    public void deleteRefreshTokenByAdminId(Long adminId) {
        refreshTokenDao.deleteRefreshTokenByAdminId(adminId);
    }


    public void deleteRefreshToken(String refreshToken) {
        refreshTokenDao.deleteRefreshToken(refreshToken);
    }
}
