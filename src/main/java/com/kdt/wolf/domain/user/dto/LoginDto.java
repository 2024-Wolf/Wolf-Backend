package com.kdt.wolf.domain.user.dto;

public class LoginDto {

    public record AdminLoginRequest(
            String loginId,
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
}
