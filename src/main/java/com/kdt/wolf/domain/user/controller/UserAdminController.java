package com.kdt.wolf.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/users")
public class UserAdminController {
    /**
     * 회원 전체 목록 조회 : `GET /users`
     * 회원 단일 정보 조회 : `GET /users/{userId}`
     * 회원 경고: `POST : /users/{userId}/warning`
     * 회원 기간 정지: `POST /users/{userId}/ban`
     * 회원 정지 해제: `POST /users/{userId}/unban`
     */

    @Operation(summary = "회원 전체 목록 조회")
    @GetMapping("")
    public String getUsers() {
        return "user";
    }

    @Operation(summary = "회원 단일 정보 조회")
    @GetMapping("/{userId}")
    public String getUser(@PathVariable Long userId) {
        return "user";
    }

    @Operation(summary = "회원 경고")
    @GetMapping("/{userId}/warning")
    public String warningUser() {
        return "user";
    }

    @Operation(summary = "회원 기간 정지")
    @GetMapping("/{userId}/ban")
    public String banUser() {
        return "user";
    }

    @Operation(summary = "회원 정지 해제")
    @GetMapping("/{userId}/unban")
    public String unbanUser() {
        return "user";
    }

}
