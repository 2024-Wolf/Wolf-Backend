package com.kdt.wolf.domain.faq.controller;

import com.kdt.wolf.domain.faq.dto.FaqDto.FaqResponse;
import com.kdt.wolf.domain.faq.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @GetMapping("")
    public String getFaqs() {
        FaqResponse response = faqService.getFaqs();
        return "faq";
    }

    @GetMapping("/{faqId}")
    public String getFaq() {
        return "";
    }

    @PostMapping("")
    public String createFaq() {
        return "redirect:/";
    }

    @PatchMapping("/{faqId}")
    public String updateFaq() {
        return "redirect:/";
    }

    @DeleteMapping("/{faqId}")
    public String deleteFaq() {
        return "redirect:/";
    }
}
