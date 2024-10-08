package com.kdt.wolf.domain.faq.controller;

import com.kdt.wolf.domain.faq.dto.FaqDto.FaqCreateRequest;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqDetail;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqResponse;
import com.kdt.wolf.domain.faq.dto.FaqDto.FaqUpdateRequest;
import com.kdt.wolf.domain.faq.service.FaqService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/faqs")
public class FaqAdminController {
    private final FaqService faqService;
    /**
     * **FAQ 전체 목록 조회**: `GET /faqs`
     * **FAQ 단일 조회**: `GET /faqs/{faqId}`
     * **FAQ 등록**: `POST /faqs`
     * **FAQ 수정**: `PATCH /faqs/{faqId}`
     * **FAQ 삭제**: `DELETE /faqs/{faqId}`
     */
    @Operation(summary = "FAQ 전체 목록 조회")
    @GetMapping("")
    public String getFaqs() {
        FaqResponse response = faqService.getFaqs();
        return "faq";
    }

    @Operation(summary = "FAQ 단일 조회")
    @GetMapping("/{faqId}")
    public String getFaq(@RequestParam Long faqId) {
        FaqDetail faqDetail = faqService.getFaqDetail(faqId);
        return "";
    }

    @Operation(summary = "FAQ 등록")
    @PostMapping("")
    public String createFaq(@RequestBody FaqCreateRequest request) {
        Long id = faqService.createFaq(request);
        return "redirect:/";
    }

    @Operation(summary = "FAQ 수정")
    @PatchMapping("/{faqId}")
    public String updateFaq(@RequestParam Long faqId, @RequestBody FaqUpdateRequest request) {
        FaqDetail faqDetail = faqService.updateFaq(faqId, request);
        return "redirect:/";
    }

    @Operation(summary = "FAQ 삭제")
    @DeleteMapping("/{faqId}")
    public String deleteFaq(@RequestParam Long faqId) {
        Long deleteId = faqService.deleteFaq(faqId);
        return "redirect:/";
    }
}
