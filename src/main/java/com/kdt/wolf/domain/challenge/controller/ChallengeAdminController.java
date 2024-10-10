package com.kdt.wolf.domain.challenge.controller;


import com.kdt.wolf.domain.challenge.dto.ChallengeDto;
import com.kdt.wolf.domain.challenge.dto.request.ChallengeCreationRequest;
import com.kdt.wolf.domain.challenge.dto.response.PaymentResponse;
import com.kdt.wolf.domain.challenge.service.ChallengeService;
import com.kdt.wolf.global.auth.dto.AuthenticatedUser;
import com.kdt.wolf.global.base.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/*")
public class ChallengeAdminController {

    private ChallengeService challengeService;

    // 챌린지 목록 조회
    @GetMapping("/challenges")
    public ApiResult<List<ChallengeDto.ChallengePreview>> getAllChallenges() {
        return ApiResult.ok(challengeService.getAllChallenges());
    }

    // 챌린지(단일) 조회
    @GetMapping("/challenge/{challengePostId}")
    public ApiResult<ChallengeDto.ChallengePreview> getChallenge(@PathVariable Long challengePostId){
        return ApiResult.ok(challengeService.getChallenge(challengePostId));
    }

    // 챌린지 생성
    @PostMapping("/challenge")
    public ApiResult<?> registerChallenge(@RequestBody ChallengeCreationRequest request, @AuthenticationPrincipal AuthenticatedUser user){
        challengeService.registerChallenge(request, user.getUserId());
        return ApiResult.ok();
    }

    // 챌린지 수정
    @PatchMapping("/challenge/{challengePostId}")
    public ApiResult<?> updateChallenge(@RequestBody ChallengeCreationRequest request, @PathVariable Long challengePostId){
        challengeService.updateChallenge(request, challengePostId);
        return ApiResult.ok();
    }

    // 챌린지 삭제
    @DeleteMapping("/challenge/{challengeId}")
    public ApiResult<?> deleteChallenge(@PathVariable Long challengeId){
        challengeService.deleteChallenge(challengeId);
        return ApiResult.ok();
    }

    // 결제 단일 조회
    @GetMapping("/payments/{payId}")
    public ApiResult<PaymentResponse> getPayment(@PathVariable Long paymentId){
        return ApiResult.ok(challengeService.getPayment(paymentId));
    }
}
