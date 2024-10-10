package com.kdt.wolf.domain.admin.dto;

public class AdminAuthDto {
    public record AdminLoginRequest(String email, String password) { }

    public record AdminLoginResponse(String accessToken, String refreshToken) { }

}
