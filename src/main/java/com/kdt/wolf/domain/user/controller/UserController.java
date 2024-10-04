package com.kdt.wolf.domain.user.controller;

import com.kdt.wolf.domain.user.dto.SignUpDto.SignUpRequest;
import com.kdt.wolf.domain.user.dto.UserDto.UserProfileDetailResponse;
import com.kdt.wolf.domain.user.dto.UserDto.UserProfileResponse;
import com.kdt.wolf.domain.user.dto.UserDto.UserUpdateRequest;
import com.kdt.wolf.domain.user.service.UserService;
import com.kdt.wolf.global.auth.dto.AuthenticatedUser;
import com.kdt.wolf.global.base.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "유저 프로필 조회")
    @GetMapping("/user/{userId}")
    public ApiResult<UserProfileResponse> getUserProfile(@PathVariable Long userId) {
        UserProfileResponse response = userService.getUserProfile(userId);
        return ApiResult.ok(response);
    }

    @Operation(summary = "마이페이지 프로필 조회")
    @GetMapping("/user/my")
    public ApiResult<UserProfileDetailResponse> getMyProfile(@AuthenticationPrincipal AuthenticatedUser user) {
        UserProfileDetailResponse response = userService.getUserProfileDetail(user.getUserId());
        return ApiResult.ok(response);
    }

    @Operation(summary = "내 프로필 수정")
    @PostMapping("/user/my")
    public ApiResult<UserProfileDetailResponse> updateMyProfile(@RequestBody UserUpdateRequest request,
                                        @AuthenticationPrincipal AuthenticatedUser user) {
        UserProfileDetailResponse response = userService.updateMyProfile(user.getUserId(), request);
        return ApiResult.ok(response);
    }

    @PostMapping("/user/sign-up")
    public ApiResult<?> completeSignUpProcess(@RequestBody SignUpRequest request,
                                              @AuthenticationPrincipal AuthenticatedUser user) {
        userService.completeSignUpProcess(user.getUserId(), request);
        return ApiResult.ok();
    }


}