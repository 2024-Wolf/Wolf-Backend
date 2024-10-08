package com.kdt.wolf.domain.faq.controller;

import com.kdt.wolf.domain.faq.dto.FaqDto.FaqResponse;
import com.kdt.wolf.domain.faq.service.FaqService;
import com.kdt.wolf.global.base.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/faqs")
public class FaqController {
    private final FaqService faqService;

    @Operation(summary = "FAQ 전체 목록 조회(유저)")
    @GetMapping("")
    public ApiResult<FaqResponse> getFaqs() {
        FaqResponse response = faqService.getFaqs();
        return ApiResult.ok(response);
    }
}
