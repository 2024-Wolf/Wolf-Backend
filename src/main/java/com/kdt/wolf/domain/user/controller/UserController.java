package com.kdt.wolf.domain.user.controller;

import com.kdt.wolf.domain.user.dto.response.UserProfileResponse;
import com.kdt.wolf.domain.user.service.UserService;
import com.kdt.wolf.global.base.ApiResult;
import com.kdt.wolf.global.dto.ApiResponse;
import com.kdt.wolf.global.message.UserMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

//    @GetMapping("/user/{userId}")
//    public ApiResponse<UserProfileResponse> getUserProfile(@PathVariable Long userId) {
//        UserProfileResponse response = userService.getUserProfile(userId);
//        return ApiResponse.createResponseWithMessage(response, UserMessage.USER_FOUND_SUCCESS);
//    }

    @PostMapping("/testinsertUser")
    public ApiResult<UserProfileResponse> insertUser() {
        UserProfileResponse response = userService.insertTestUser();
        return ApiResult.created(response);
    }


    @GetMapping("/user/{userId}")
    public ApiResult<UserProfileResponse> getUserProfile(@PathVariable Long userId) {
        UserProfileResponse response = userService.getUserProfile(userId);
        return ApiResult.ok(response);
    }
}