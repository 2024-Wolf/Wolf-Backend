package com.kdt.wolf.global.auth.dto;

import com.kdt.wolf.domain.user.dto.LoginFlag;

public class LoginDto {

    public record AdminLoginRequest(
            String email,
            String password
    ) {}
    public record GoogleLoginRequest(
            String idToken
    ) {}
    public record ReissueAccessTokenRequest(
            String accessToken,
            String refreshToken
    ) {}
    public record TokenResponse(
            String grantType,
            String accessToken,
            String refreshToken,
            Long accessTokenExpiresIn

    ) { }
    public record GoogleLoginResponse(
        TokenResponse tokenResponse,
        LoginFlag loginFlag
    ) {}
    public record LogoutRequest(
            String refreshToken,
            String fcmToken
    ) {}
    public record AdminLogoutRequest(
            String refreshToken
    ) {}
}
