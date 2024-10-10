package com.kdt.wolf.domain.admin.service;

import com.kdt.wolf.domain.admin.dao.AdminDao;
import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.auth.dto.LoginDto.AdminLoginRequest;
import com.kdt.wolf.global.auth.dto.LoginDto.AdminLogoutRequest;
import com.kdt.wolf.global.auth.dto.LoginDto.TokenResponse;
import com.kdt.wolf.global.auth.provider.JwtTokenProvider;
import com.kdt.wolf.global.auth.service.RefreshTokenService;
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminAuthService {
    private final RefreshTokenService refreshTokenService;
    private final AdminDao adminDao;
    private final JwtTokenProvider tokenProvider;

    public TokenResponse login(AdminLoginRequest request) {
        AdminEntity admin = adminDao.signIn(request.email(), request.password());

        return generateJwtTokenResponse(admin);
    }

    public void logout(AdminLogoutRequest request) {
        refreshTokenService.deleteRefreshToken(request.refreshToken());
    }

    public TokenResponse reissueAccessToken(String accessToken, String refreshToken) {
        validateRefreshToken(refreshToken);

        long adminId = Long.parseLong(tokenProvider.getSubject(accessToken));
        AdminEntity admin = adminDao.findById(adminId);

        return generateJwtTokenResponse(admin);
    }

    private TokenResponse generateJwtTokenResponse(AdminEntity admin) {
        refreshTokenService.deleteRefreshTokenByAdminId(admin.getAdminId());

        TokenResponse tokenResponse = tokenProvider.createJwtTokenResponse(admin);
        refreshTokenService.saveRefreshToken(admin, tokenResponse.refreshToken());

        return tokenResponse;
    }

    private void validateRefreshToken(String refreshToken) {
        try {
            tokenProvider.validateToken(refreshToken);
        } catch (Exception e) {
            throw new BusinessException(ExceptionCode.REFRESH_TOKEN_VALIDATION_FAILED);
        }
    }
}
