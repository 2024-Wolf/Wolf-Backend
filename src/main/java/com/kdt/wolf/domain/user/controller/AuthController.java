package com.kdt.wolf.domain.user.controller;

import com.kdt.wolf.domain.user.dto.LoginDto;
import com.kdt.wolf.domain.user.service.AuthService;
import com.kdt.wolf.global.base.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/*")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login")
    @ResponseStatus(code = HttpStatus.CREATED) //`201 Created`로 응답
    public ApiResult<?> login() {
        return ApiResult.ok();
    }

    @GetMapping("/google")
    public ApiResult<?> google(@RequestBody LoginDto.googleLoginRequest request) {
        authService.googleLogin(request.idToken());

        return ApiResult.ok();
    }

}
