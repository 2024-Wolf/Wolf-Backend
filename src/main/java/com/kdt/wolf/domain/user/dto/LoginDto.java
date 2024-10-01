package com.kdt.wolf.domain.user.dto;

public class LoginDto {

    public record AdminLoginRequest(
            String loginId,
            String password
    ) {}
    public record googleLoginRequest(
            String idToken
    ) {}
    public record TokenResponse(
            String grantType,
            String accessToken,
            String refreshToken,
            Long accessTokenExpiresIn
    ) {
        public TokenResponse(String grantType, String accessToken, String refreshToken, Long accessTokenExpiresIn) {
            this.accessToken = accessToken;
            this.grantType = grantType;
            this.refreshToken = refreshToken;
            this.accessTokenExpiresIn = accessTokenExpiresIn;
        }
    }
}
