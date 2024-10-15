package com.kdt.wolf.domain.faq.controller;

import com.kdt.wolf.domain.faq.dto.response.FaqPageResponse;
import com.kdt.wolf.domain.faq.entity.FaqCategory;
import com.kdt.wolf.domain.faq.service.FaqService;
import com.kdt.wolf.global.base.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/faqs")
public class FaqController {
    private final FaqService faqService;

    @Operation(summary = "FAQ 전체 목록 조회(유저)")
    @GetMapping("/{category}")
    public ApiResult<FaqPageResponse> getFaqs(@PathVariable String category,
                                              @PageableDefault(page = 0, size = 20) Pageable pageable) {
        FaqCategory faqCategory = FaqCategory.valueOf(category);
        FaqPageResponse response = faqService.getFaqsByCategory(faqCategory, pageable);
        return ApiResult.ok(response);
    }
}
