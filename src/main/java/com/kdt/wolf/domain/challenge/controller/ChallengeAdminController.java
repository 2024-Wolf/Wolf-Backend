package com.kdt.wolf.domain.challenge.controller;


import com.kdt.wolf.domain.admin.repository.AdminRepository;
import com.kdt.wolf.domain.challenge.dto.ChallengeAdminDto.VerificationDetail;
import com.kdt.wolf.domain.challenge.dto.ChallengeAdminDto.VerificationPageResponse;
import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengeAdminPageResponse;
import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengeDetail;
import com.kdt.wolf.domain.challenge.dto.request.ChallengeCreationRequest.ChallengeCreateRequest;
import com.kdt.wolf.domain.challenge.dto.request.ChallengeVerificationRequest.VerificationRequest;
import com.kdt.wolf.domain.challenge.dto.response.PaymentResponse;
import com.kdt.wolf.domain.challenge.service.ChallengeService;
import com.kdt.wolf.global.auth.dto.AuthenticatedUser;
import com.kdt.wolf.global.base.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/challenges")
public class ChallengeAdminController {

    private final ChallengeService challengeService;
    private final AdminRepository adminRepository;
    //TODO : 리턴값 ApiResult<?> -> String 변경 필요

    // 챌린지 목록 조회
    @Operation(summary = "챌린지 목록 조회")
    @GetMapping
    public String getAllChallenges(Model model, @PageableDefault(page = 0, size = 20) Pageable pageable) {
        ChallengeAdminPageResponse challenges = challengeService.getAllChallenges(pageable);
        model.addAttribute("challenges", challenges.challenges());
        model.addAttribute("page", challenges.page());
        return "challenge";
    }

    // 챌린지 단일 정보 조회
    @Operation(summary = "챌린지 단일 정보 조회")
    @GetMapping("/{challengeId}")
    public String getChallenges(@PathVariable Long challengeId, Model model) {
        ChallengeDetail challenge = challengeService.getChallengeDetail(challengeId);
        model.addAttribute("challenge", challenge);
        return "challengeDetail";
    }

    // 챌린지 생성
    @Operation(summary = "챌린지 생성")
    @PostMapping
    public String registerChallenge(@ModelAttribute ChallengeCreateRequest request, @AuthenticationPrincipal AuthenticatedUser user){
        Long challengeId = challengeService.registerChallenge(request, adminRepository.findAll().get(0).getAdminId());
        return "redirect:/admin/challenges/" + challengeId;
    }

    // 챌린지 목록 조회
    @Operation(summary = "챌린지 수정 페이지")
    @GetMapping("/challengeEdit/{challengeId}")
    public String getEditChallengeView(@PathVariable Long challengeId, Model model) {
        ChallengeDetail challenge = challengeService.getChallengeDetail(challengeId);
        model.addAttribute("challenge", challenge);
        return "challengeEdit";
    }

    // 챌린지 수정
    @Operation(summary = "챌린지 수정")
    @PutMapping("/{challengeId}")
    public String  updateChallenge(@ModelAttribute ChallengeCreateRequest request,
                                   @PathVariable Long challengeId,
                                   Model model){
        Long updatedChallengeId = challengeService.updateChallenge(request, challengeId);
        ChallengeDetail challenge = challengeService.getChallengeDetail(updatedChallengeId);
        model.addAttribute("challenge", challenge);
        return "redirect:/admin/challenges/{challengeId}";
    }

    // 챌린지 삭제
    @Operation(summary = "챌린지 삭제")
    @DeleteMapping("/{challengeId}")
    public String deleteChallenge(@PathVariable Long challengeId){
        challengeService.deleteChallenge(challengeId);
        return "redirect:/admin/challenges";
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
    public String getAllVerifications(Model model, @PageableDefault(page = 0, size = 20) Pageable pageable){
        VerificationPageResponse response = challengeService.getAllVerifications(pageable);
        model.addAttribute("verifications", response.verifications());
        model.addAttribute("page", response.page());
        return "auth";
    }

    // 인증 단일 조회
    @Operation(summary = "인증 단일 조회")
    @GetMapping("/verification/{verificationId}")
    public String getVerification(@PathVariable Long verificationId, Model model){
        VerificationDetail response = challengeService.getVerification(verificationId);
        model.addAttribute("verification", response);
        return "authDetail";
    }

    // 인증 상태 변경
    @Operation(summary = "인증 상태 변경")
    @PutMapping("/verification/{verificationId}")
    public String editVerificationStatus(@PathVariable Long verificationId, @ModelAttribute VerificationRequest request, Model model){
        Long id = challengeService.updateVerification(request, verificationId);
        VerificationDetail response = challengeService.getVerification(id);
        model.addAttribute("verification", response);
        return "redirect:/admin/challenges/verifications";
    }

}
