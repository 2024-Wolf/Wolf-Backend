package com.kdt.wolf.domain.faq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/faqs")
public class FaqAdminController {
    /**
     * **FAQ 전체 목록 조회**: `GET /faqs`
     * **FAQ 단일 조회**: `GET /faqs/{faqId}`
     * **FAQ 등록**: `POST /faqs`
     * **FAQ 수정**: `PATCH /faqs/{faqId}`
     * **FAQ 삭제**: `DELETE /faqs/{faqId}`
     */
    @GetMapping("")
    public String getFaqs() {
        return "admin/faq/list";
    }

    @GetMapping("/{faqId}")
    public String getFaq() {
        return "admin/faq/detail";
    }

    @PostMapping("")
    public String createFaq() {
        return "redirect:/admin/faqs";
    }

    @PatchMapping("/{faqId}")
    public String updateFaq() {
        return "redirect:/admin/faqs";
    }

    @DeleteMapping("/{faqId}")
    public String deleteFaq() {
        return "redirect:/admin/faqs";
    }
}
