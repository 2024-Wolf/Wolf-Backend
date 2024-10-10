package com.kdt.wolf.domain.admin.controller;

import com.kdt.wolf.domain.admin.dto.AdminAuthDto.AdminLoginRequest;
import com.kdt.wolf.domain.admin.service.AdminAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/admin/auth/*")
public class AdminAuthController {
    private final AdminAuthService adminService;

    @PostMapping("/login")
    public String login(@RequestBody AdminLoginRequest request) {
        adminService.login(request);
        return "redirect:/";
    }
}
