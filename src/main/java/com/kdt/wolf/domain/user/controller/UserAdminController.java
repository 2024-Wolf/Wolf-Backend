package com.kdt.wolf.domain.user.controller;

import com.kdt.wolf.domain.user.dto.UserAdminDto.UserDetailResponse;
import com.kdt.wolf.domain.user.dto.UserAdminDto.UserPreviewResponse;
import com.kdt.wolf.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/users")
public class UserAdminController {
    private final UserService userService;
    /**
     * 회원 전체 목록 조회 : `GET /users`
     * 회원 단일 정보 조회 : `GET /users/{userId}`
     * TODO : 회원 경고: `POST : /users/{userId}/warning`
     * TODO : 회원 기간 정지: `POST /users/{userId}/ban`
     * TODO : 회원 정지 해제: `POST /users/{userId}/unban`
     */

    @Operation(summary = "회원 전체 목록 조회")
    @GetMapping("")
    public String getUsers() {
        List<UserPreviewResponse> users = userService.getUserList();
        return "user";
    }

    @Operation(summary = "회원 단일 정보 조회")
    @GetMapping("/{userId}")
    public String getUser(@PathVariable Long userId) {
        UserDetailResponse user = userService.getUserDetail(userId);
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
