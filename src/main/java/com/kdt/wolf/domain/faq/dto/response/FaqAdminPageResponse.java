package com.kdt.wolf.domain.faq.dto.response;

import com.kdt.wolf.domain.faq.dto.FaqDto.FaqAdminResponse;
import com.kdt.wolf.global.dto.PageResponse;

import java.util.List;

public record FaqAdminPageResponse (
    List<FaqAdminResponse> faqItems,
    PageResponse page
){}
