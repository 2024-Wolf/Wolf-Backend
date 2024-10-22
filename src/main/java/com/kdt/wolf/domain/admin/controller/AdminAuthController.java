package com.kdt.wolf.domain.admin.controller;

import com.kdt.wolf.domain.admin.service.AdminAuthService;
import com.kdt.wolf.global.auth.dto.LoginDto.AdminLoginRequest;
import com.kdt.wolf.global.auth.dto.LoginDto.AdminLogoutRequest;
import com.kdt.wolf.global.auth.dto.LoginDto.ReissueAccessTokenRequest;
import com.kdt.wolf.global.auth.dto.LoginDto.TokenResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/auth")
public class AdminAuthController {
    private final AdminAuthService adminService;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        AdminLoginRequest request = new AdminLoginRequest(username, password);
        TokenResponse tokenResponse = adminService.login(request);

        // JWT와 Refresh Token을 세션에 저장
        session.setAttribute("JWT", tokenResponse.accessToken());
        session.setAttribute("REFRESH_TOKEN", tokenResponse.refreshToken());

//        AdminEntity admin = adminService.getAdminByUsername(username);
//        session.setAttribute("admin", admin);

        System.out.println("JWT: " + tokenResponse.accessToken());
        System.out.println("REFRESH_TOKEN: " + tokenResponse.refreshToken());


        return "redirect:/admin/notices";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        String refreshToken = (String) session.getAttribute("REFRESH_TOKEN");

        // 리프레시 토큰이 있을 때만 로그아웃 처리
        if (refreshToken != null) {
            adminService.logout(new AdminLogoutRequest(refreshToken));
        } else {
            System.out.println("세션에 리프레시 토큰이 없습니다.");
        }
        session.removeAttribute("JWT");
        session.removeAttribute("REFRESH_TOKEN");

        // 세션 무효화
        session.invalidate();
        return "redirect:/adminLogin";
    }

    @PostMapping("/reissue")
    public String reissueAccessToken(@RequestBody ReissueAccessTokenRequest request) {
        TokenResponse response = adminService.reissueAccessToken(request.accessToken(), request.refreshToken());
        return "redirect:/";
    }
}
