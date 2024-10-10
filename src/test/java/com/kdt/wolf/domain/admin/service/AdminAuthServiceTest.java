package com.kdt.wolf.domain.admin.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kdt.wolf.domain.admin.dao.AdminDao;
import com.kdt.wolf.domain.admin.entity.AdminEntity;
import com.kdt.wolf.global.auth.dto.LoginDto.AdminLoginRequest;
import com.kdt.wolf.global.auth.dto.LoginDto.TokenResponse;
import com.kdt.wolf.global.auth.provider.JwtTokenProvider;
import com.kdt.wolf.global.auth.service.RefreshTokenService;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AdminAuthServiceTest {
    @InjectMocks
    private AdminAuthService adminAuthService;

    @Mock
    private RefreshTokenService refreshTokenService;

    @Mock
    private AdminDao adminDao;

    @Mock
    private JwtTokenProvider tokenProvider;

    private AdminEntity adminEntity;
    private AdminLoginRequest loginRequest;
    private TokenResponse tokenResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Test 데이터 초기화
        adminEntity = AdminEntity.builder()
                .email("admin@test.com")
                .password("password")
                .nickname("admin")
                .name("Admin Name")
                .build();

        loginRequest = new AdminLoginRequest("admin@test.com", "password");

        tokenResponse = new TokenResponse("Bearer", "accessToken", "refreshToken", 3600L);
    }

    @Test
    void login_Success() {
        // given
        when(adminDao.signIn(loginRequest.email(), loginRequest.password())).thenReturn(adminEntity);
        when(tokenProvider.createJwtTokenResponse(adminEntity)).thenReturn(tokenResponse);

        // when
        TokenResponse response = adminAuthService.login(loginRequest);

        // then
        assertNotNull(response);
        assertEquals(tokenResponse.accessToken(), response.accessToken());
        assertEquals(tokenResponse.refreshToken(), response.refreshToken());

        // verify: 메서드들이 올바르게 호출되었는지 확인
        verify(adminDao, times(1)).signIn(loginRequest.email(), loginRequest.password());
        verify(tokenProvider, times(1)).createJwtTokenResponse(adminEntity);
        verify(refreshTokenService, times(1)).saveRefreshToken(adminEntity, tokenResponse.refreshToken());
    }

    @Test
    void login_Fail_AdminNotFound() {
        // given: AdminEntity를 찾지 못할 때의 시나리오
        when(adminDao.signIn(loginRequest.email(), loginRequest.password()))
                .thenThrow(new NotFoundException(ExceptionCode.NOT_FOUND_ADMIN));

        // when & then
        Exception exception = assertThrows(NotFoundException.class, () -> {
            adminAuthService.login(loginRequest);
        });

        verify(adminDao, times(1)).signIn(loginRequest.email(), loginRequest.password());
        verify(tokenProvider, times(0)).createJwtTokenResponse(any(AdminEntity.class));
        verify(refreshTokenService, times(0)).saveRefreshToken(any(AdminEntity.class), anyString());
    }
}