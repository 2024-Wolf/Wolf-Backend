package com.kdt.wolf.domain.challenge.controller;


import com.kdt.wolf.domain.challenge.dto.ChallengeAdminDto.VerificationDetail;
import com.kdt.wolf.domain.challenge.dto.ChallengeAdminDto.VerificationPreview;
import com.kdt.wolf.domain.challenge.dto.ChallengeDto;
import com.kdt.wolf.domain.challenge.dto.request.ChallengeCreationRequest;
import com.kdt.wolf.domain.challenge.dto.response.PaymentResponse;
import com.kdt.wolf.domain.challenge.service.ChallengeService;
import com.kdt.wolf.global.auth.dto.AuthenticatedUser;
import com.kdt.wolf.global.base.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class ChallengeAdminController {

    private final ChallengeService challengeService;
    //TODO : 리턴값 ApiResult<?> -> String 변경 필요

    // 챌린지 생성
    @Operation(summary = "챌린지 생성")
    @PostMapping("/challenge")
    public ApiResult<?> registerChallenge(@RequestBody ChallengeCreationRequest request, @AuthenticationPrincipal AuthenticatedUser user){
        challengeService.registerChallenge(request, user.getUserId());
        return ApiResult.ok();
    }

    // 챌린지 목록 조회
    @Operation(summary = "챌린지 목록 조회")
    @GetMapping("/challenges")
    public ApiResult<List<ChallengeDto.ChallengePreview>> getAllChallenges() {
        return ApiResult.ok(challengeService.getAllChallenges());
    }

    // 챌린지 수정
    @Operation(summary = "챌린지 수정")
    @PatchMapping("/challenge/{challengePostId}")
    public ApiResult<?> updateChallenge(@RequestBody ChallengeCreationRequest request, @PathVariable Long challengePostId){
        challengeService.updateChallenge(request, challengePostId);
        return ApiResult.ok();
    }

    // 챌린지 삭제
    @Operation(summary = "챌린지 삭제")
    @DeleteMapping("/challenge/{challengeId}")
    public ApiResult<?> deleteChallenge(@PathVariable Long challengeId){
        challengeService.deleteChallenge(challengeId);
        return ApiResult.ok();
    }

    // 결제 단일 조회
    @Operation(summary = "결제 단일 조회")
    @GetMapping("/payment/{paymentId}")
    public ApiResult<PaymentResponse> getPayment(@PathVariable Long paymentId){
        return ApiResult.ok(challengeService.getPayment(paymentId));
    }

    // 인증 전체 조회
    @Operation(summary = "인증 전체 조회")
    @GetMapping("/verifications")
    public String getAllVerifications(){
        List<VerificationPreview> response = challengeService.getAllVerifications();
        return "";
    }

    // 인증 단일 조회
    @Operation(summary = "인증 단일 조회")
    @GetMapping("/verification/{verificationId}")
    public String getVerification(@PathVariable Long verificationId){
        VerificationDetail response = challengeService.getVerification(verificationId);
        return "";
    }

}
