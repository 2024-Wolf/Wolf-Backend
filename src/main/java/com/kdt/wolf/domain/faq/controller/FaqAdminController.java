package com.kdt.wolf.domain.faq.controller;

import com.kdt.wolf.domain.admin.repository.AdminRepository;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqCreateRequest;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqDetail;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqUpdateRequest;
import com.kdt.wolf.domain.faq.dto.response.FaqAdminPageResponse;
import com.kdt.wolf.domain.faq.service.FaqService;
import com.kdt.wolf.global.auth.dto.AuthenticatedUser;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/faqs")
public class FaqAdminController {
    private final FaqService faqService;
    private final AdminRepository adminRepository;

    /**
     * **FAQ 전체 목록 조회**: `GET /faqs`
     * **FAQ 단일 조회**: `GET /faqs/{faqId}`
     * **FAQ 등록**: `POST /faqs`
     * **FAQ 수정**: `PATCH /faqs/{faqId}`
     * **FAQ 삭제**: `DELETE /faqs/{faqId}`
     */
    @Operation(summary = "FAQ 전체 목록 조회")
    @GetMapping
    public String getFaqs(Model model, @PageableDefault(page = 0, size = 20) Pageable pageable) {
        FaqAdminPageResponse response = faqService.getFaqs(pageable);
        model.addAttribute("faqList", response);
        return "faq";
    }

    @Operation(summary = "FAQ 단일 조회")
    @GetMapping("/{faqId}")
    public String getFaq(@PathVariable Long faqId, Model model) {
        FaqDetail faqDetail = faqService.getFaqDetail(faqId);
        model.addAttribute("faqContent", faqDetail);
        return "faqDetail";
    }

    @Operation(summary = "FAQ 수정 화면")
    @GetMapping("/faqEdit/{faqId}")
    public String editFaqView(@PathVariable Long faqId, Model model) {
        FaqDetail faqDetail = faqService.getFaqDetail(faqId);
        model.addAttribute("faqContent", faqDetail);
        return "faqEdit";
    }

    @Operation(summary = "FAQ 등록")
    @PostMapping
    public String createFaq(@AuthenticationPrincipal AuthenticatedUser author, @ModelAttribute FaqCreateRequest request) {
        Long id = faqService.createFaq(adminRepository.findAll().get(0).getAdminId(), request);
        return "redirect:/admin/faqs/" + id;
    }

    @Operation(summary = "FAQ 수정")
    @PutMapping("/{faqId}")
    public String updateFaq(@PathVariable Long faqId, @ModelAttribute FaqUpdateRequest request, Model model) {
        System.out.println(request);
        FaqDetail faqDetail = faqService.updateFaq(faqId, request);
        model.addAttribute("faqContent", faqDetail);
        return "redirect:/admin/faqs/{faqId}";
    }

    @Operation(summary = "FAQ 삭제")
    @DeleteMapping("/{faqId}")
    public String deleteFaq(@PathVariable Long faqId) {
        Long deleteId = faqService.deleteFaq(faqId);
        return "redirect:/admin/faqs";
    }
}
