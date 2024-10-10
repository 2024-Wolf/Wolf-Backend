package com.kdt.wolf.domain.admin.service;

import com.kdt.wolf.domain.admin.dao.AdminDao;
import com.kdt.wolf.domain.admin.dto.AdminAuthDto.AdminLoginRequest;
import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.global.auth.dto.LoginDto.TokenResponse;
import com.kdt.wolf.global.auth.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminAuthService {
    private final AdminDao adminDao;
    private final JwtTokenProvider tokenProvider;

    public TokenResponse login(AdminLoginRequest request) {
        AdminEntity admin = adminDao.signIn(request.email(), request.password());

        return tokenProvider.createJwtTokenResponse(admin);
    }

}
