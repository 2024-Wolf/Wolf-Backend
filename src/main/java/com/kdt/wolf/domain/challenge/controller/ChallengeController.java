package com.kdt.wolf.domain.challenge.controller;


import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview;
import com.kdt.wolf.domain.challenge.dto.request.ChallengeCreationRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengePaymentRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengeRegistrationRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengeVerificationRequest;
import com.kdt.wolf.domain.challenge.service.ChallengeService;
import com.kdt.wolf.global.auth.dto.AuthenticatedUser;
import java.util.Map;

import com.kdt.wolf.global.base.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/*")
public class ChallengeController {

    private final ChallengeService challengeService;

    // 챌린지 목록 조회
    @GetMapping("/challenges/{groupPostId}")
    public ApiResult<Map<String,List<ChallengePreview>>> getAllChallenges(@PathVariable Long groupPostId, @AuthenticationPrincipal AuthenticatedUser user) {
        return ApiResult.ok(challengeService.getAllChallenges(groupPostId, user.getUserId()));
    }

    // 챌린지(단일) 조회
    @GetMapping("/challenge/{challengePostId}")
    public ApiResult<ChallengePreview> getChallenge(@PathVariable Long challengePostId){
        return ApiResult.ok(challengeService.getChallenge(challengePostId));
    }

    // 그룹장 신청
    @PostMapping("/registration")
    public ApiResult<?> challengeRegistration(@RequestBody ChallengeRegistrationRequest request){
        challengeService.createChallengeRegistration(request);
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
    public ApiResult<?> challengeVerification(@RequestBody ChallengeVerificationRequest request, @AuthenticationPrincipal AuthenticatedUser user){
        challengeService.updateVerification(request, user.getUserId());
        return ApiResult.ok();
    }

}
