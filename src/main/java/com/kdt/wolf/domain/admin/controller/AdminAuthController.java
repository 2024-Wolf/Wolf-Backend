package com.kdt.wolf.domain.admin.controller;

import com.kdt.wolf.domain.admin.service.AdminAuthService;
import com.kdt.wolf.global.auth.dto.LoginDto.AdminLoginRequest;
import com.kdt.wolf.global.auth.dto.LoginDto.AdminLogoutRequest;
import com.kdt.wolf.global.auth.dto.LoginDto.ReissueAccessTokenRequest;
import com.kdt.wolf.global.auth.dto.LoginDto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/admin/auth/")
public class AdminAuthController {
    private final AdminAuthService adminService;

    @PostMapping("/login")
    public String login(@RequestBody AdminLoginRequest request) {
        TokenResponse response = adminService.login(request);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(@RequestBody AdminLogoutRequest request) {
        adminService.logout(request);
        return "redirect:/";
    }

    @PostMapping("/reissue")
    public String reissueAccessToken(@RequestBody ReissueAccessTokenRequest request) {
        TokenResponse response = adminService.reissueAccessToken(request.accessToken(), request.refreshToken());
        return "redirect:/";
    }
}
