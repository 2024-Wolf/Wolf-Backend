package com.kdt.wolf.domain.user.controller;

import com.kdt.wolf.domain.alert.service.AlertService;
import com.kdt.wolf.domain.alert.dto.AlertDto.AlertResponse;
import com.kdt.wolf.domain.user.dto.SignUpDto.SignUpRequest;
import com.kdt.wolf.domain.user.dto.UserDto.UserProfileDetailResponse;
import com.kdt.wolf.domain.user.dto.UserDto.UserProfileResponse;
import com.kdt.wolf.domain.user.dto.UserDto.UserUpdateRequest;
import com.kdt.wolf.domain.user.service.UserService;
import com.kdt.wolf.global.auth.dto.AuthenticatedUser;
import com.kdt.wolf.global.base.ApiResult;
import com.kdt.wolf.global.util.FileValidationUtil;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Objects;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AlertService alertService;

    @Operation(summary = "유저 프로필 조회")
    @GetMapping("/{userId}")
    public ApiResult<UserProfileResponse> getUserProfile(@PathVariable Long userId) {
        UserProfileResponse response = userService.getUserProfile(userId);
        return ApiResult.ok(response);
    }

    @Operation(summary = "마이페이지 프로필 조회")
    @GetMapping("/my")
    public ApiResult<UserProfileDetailResponse> getMyProfile(@AuthenticationPrincipal AuthenticatedUser user) {
        UserProfileDetailResponse response = userService.getUserProfileDetail(user.getUserId());
        return ApiResult.ok(response);
    }

    @Operation(summary = "내 프로필 수정")
    @PostMapping("/my")
    public ApiResult<UserProfileDetailResponse> updateMyProfile(@RequestBody UserUpdateRequest request,
                                        @AuthenticationPrincipal AuthenticatedUser user) {
        UserProfileDetailResponse response = userService.updateMyProfile(user.getUserId(), request);
        return ApiResult.ok(response);
    }

    @Operation(summary = "프로필 사진 변경")
    @PostMapping(value = "/my/profile-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResult<String> updateProfileImage(@RequestParam("profileImage") MultipartFile profileImage,
                                                @AuthenticationPrincipal AuthenticatedUser user) {
        // 이미지 파일 유효성 검사
        FileValidationUtil.validateImageFile(profileImage);

        // 서비스 레이어에서 파일 저장 및 URL 생성
        String profileImageUrl = userService.updateProfileImage(user.getUserId(), profileImage);

        return ApiResult.ok(profileImageUrl);
    }

    @Operation(summary = "사용 가능한 닉네임 조회 / true : 사용 가능")
    @GetMapping("/nickname")
    public ApiResult<String> isNicknameAvailable (@RequestParam String nickname, @AuthenticationPrincipal AuthenticatedUser user) {
        String isAvailable = nickname.equals(user.getNickname()) ? "equal" : userService.isNicknameAvailable(nickname);
        return ApiResult.ok(isAvailable);
    }

    @PostMapping("/sign-up")
    public ApiResult<?> completeSignUpProcess(@RequestBody SignUpRequest request,
                                              @AuthenticationPrincipal AuthenticatedUser user) {
        userService.completeSignUpProcess(user.getUserId(), request);
        return ApiResult.ok();
    }

    @Operation(summary = "알람 전체 조회")
    @GetMapping("/alarms")
    public ApiResult<List<AlertResponse>> getAlarms(@AuthenticationPrincipal AuthenticatedUser user) {

        List<AlertResponse> response = alertService.getAlarms(user.getUserId());
        return ApiResult.ok(response);
    }

    @Operation(summary = "알람 미리보기 조회")
    @GetMapping("/alarms/preview")
    public ApiResult<List<AlertResponse>> getAlarmsPreview(@AuthenticationPrincipal AuthenticatedUser user) {
        List<AlertResponse> response = alertService.getAlarmsPreview(user.getUserId());
        return ApiResult.ok(response);
    }

    @Operation(summary = "알람 읽음 처리")
    @PostMapping("/alarms/{alertId}")
    public ApiResult<Long> readAlarm(@PathVariable Long alertId) {
        Long alarmId = alertService.readAlarm(alertId);
        return ApiResult.ok(alarmId);
    }
}