package com.kdt.wolf.domain.user.dto;

public class LoginDto {

    public record AdminLoginRequest(String loginId, String password) {}
    public record googleLoginRequest(String idToken) {}
}
