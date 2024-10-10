package com.kdt.wolf.domain.user.service;

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
        if(!validateRefreshToken(refreshToken)) {
            throw new UnauthorizedException();
        }
        refreshTokenDao.saveRefreshToken(user, refreshToken);
    }

    public boolean validateRefreshToken(String refreshToken) {
        tokenProvider.validateToken(refreshToken);
        Date refreshTokenExpireTime = getRefreshTokenExpireTime(refreshToken);
        return !refreshTokenExpireTime.before(new Date());
    }

    private Date getRefreshTokenExpireTime(String refreshToken) {
        return tokenProvider.getExpirationDateFromToken(refreshToken);
    }

    public void deleteRefreshTokenByUserId(Long userId) {
        refreshTokenDao.deleteRefreshTokenByUserId(userId);
    }

    public void deleteRefreshToken(String refreshToken) {
        refreshTokenDao.deleteRefreshToken(refreshToken);
    }
}
