package com.kdt.wolf.domain.admin.service;

import com.kdt.wolf.domain.admin.dao.AdminDao;
import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.global.auth.dto.LoginDto.AdminLoginRequest;
import com.kdt.wolf.global.auth.dto.LoginDto.AdminLogoutRequest;
import com.kdt.wolf.global.auth.dto.LoginDto.TokenResponse;
import com.kdt.wolf.global.auth.provider.JwtTokenProvider;
import com.kdt.wolf.global.auth.service.RefreshTokenService;
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

        TokenResponse tokenResponse = tokenProvider.createJwtTokenResponse(admin);
        refreshTokenService.saveRefreshToken(admin, tokenResponse.refreshToken());

        return tokenResponse;
    }
    public void logout(AdminLogoutRequest request) {
        refreshTokenService.deleteRefreshToken(request.refreshToken());
    }
}
