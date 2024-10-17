package com.kdt.wolf.domain.faq.dto.response;

import com.kdt.wolf.domain.faq.dto.FaqDto.FaqItems;
import com.kdt.wolf.global.dto.PageResponse;
import java.util.List;

public record FaqPageResponse (
        List<FaqItems> faqItems,
        PageResponse page
){}

