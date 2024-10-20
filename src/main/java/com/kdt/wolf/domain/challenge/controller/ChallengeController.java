package com.kdt.wolf.domain.challenge.controller;


import com.kdt.wolf.domain.challenge.dto.ChallengeDto;
import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePageResponse;
import com.kdt.wolf.domain.challenge.dto.ChallengeStatus;
import com.kdt.wolf.domain.challenge.dto.request.ChallengePaymentRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengeRegistrationRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengeVerificationRequest;
import com.kdt.wolf.domain.challenge.service.ChallengeService;
import com.kdt.wolf.global.auth.dto.AuthenticatedUser;
import io.swagger.v3.oas.annotations.Operation;

import com.kdt.wolf.global.base.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/*")
public class ChallengeController {

    private final ChallengeService challengeService;

    // 챌린지(단일) 조회
    @GetMapping("/challenge/{challengePostId}")
    public ApiResult<ChallengeDto.ChallengeDetail> getChallenge(@PathVariable Long challengePostId){
        return ApiResult.ok(challengeService.getChallenge(challengePostId));
    }

    // 챌린지 상태별 목록 조회
    @Operation(summary = "챌린지 상태별 목록 조회 / status : CERTIFICATION, CERTIFICATION_COMPLETE, RESULT_CONFIRM, APPLY, PARTICIPATE, PAY")
    @GetMapping("/challenges/{groupPostId}/{status}")
    public ApiResult<ChallengePageResponse> getChallengesByStatus(@PathVariable Long groupPostId,
                                                                  @PathVariable String status,
                                                                  @AuthenticationPrincipal AuthenticatedUser user,
                                                                  @PageableDefault(size = 5) Pageable pageable){
        ChallengeStatus challengeStatus = ChallengeStatus.valueOf(status);
        ChallengePageResponse response = challengeService.getChallengesByStatus(challengeStatus, groupPostId, user.getUserId(), pageable);
        return ApiResult.ok(response);
    }

    // 그룹장 신청
    @PostMapping("/registration")
    public ApiResult<?> challengeRegistration(@RequestBody ChallengeRegistrationRequest request, @AuthenticationPrincipal AuthenticatedUser user){
        challengeService.createChallengeRegistration(request, user.getUserId());
        return ApiResult.ok();
    }

    // 그룹원 참여
    @PostMapping("/registrations")
    public ApiResult<?> challengeRegistrations(@RequestBody ChallengeRegistrationRequest request, @AuthenticationPrincipal AuthenticatedUser user){
        challengeService.createChallengeRegistrations(request, user.getUserId());
        return ApiResult.ok();
    }

    // 챌린지 결제
    @PostMapping("/payment")
    public ApiResult<?> challengePayment(@RequestBody ChallengePaymentRequest request, @AuthenticationPrincipal AuthenticatedUser user){
        challengeService.challengePayment(request, user.getUserId());
        return ApiResult.ok();
    }

    // 챌린지 인증
    @PostMapping("/challenge/verification")
    public ApiResult<?> challengeVerification(@RequestBody ChallengeVerificationRequest.VerificationRequest request, @AuthenticationPrincipal AuthenticatedUser user){
        challengeService.updateVerification(request, user.getUserId());
        return ApiResult.ok();
    }

}
