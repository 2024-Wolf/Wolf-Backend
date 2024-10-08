package com.kdt.wolf.domain.faq.dto;

import com.kdt.wolf.domain.faq.entity.FaqCategory;
import java.util.List;
import java.util.Map;

public class FaqDto {
    public record FaqResponse (
            Map<String, List<FaqItems>> faqItems
    ) {}

    public record FaqItems (
            String question,
            String answer
    ) {}

    public record FaqDetail (
            FaqCategory category,
            String question,
            String answer,
            String author,
            String createdAt,
            String updatedAt
    ) {}
    public record FaqCreateRequest (
            String category,
            String question,
            String answer,
            Long adminId
    ) {}
    public record FaqUpdateRequest (
            String category,
            String question,
            String answer
    ) {}
}
