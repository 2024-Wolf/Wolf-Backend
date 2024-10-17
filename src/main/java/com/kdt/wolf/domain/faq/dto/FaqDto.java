package com.kdt.wolf.domain.faq.dto;

import com.kdt.wolf.domain.faq.entity.FaqCategory;

import java.time.LocalDate;
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
            Long faqId,
            FaqCategory category,
            String question,
            String answer,
            String author,
            LocalDate createdAt,
            LocalDate updatedAt
    ) {}
    public record FaqCreateRequest (
            String category,
            String question,
            String answer
    ) {}
    public record FaqUpdateRequest (
            String category,
            String question,
            String answer
    ) {}
    public record FaqAdminResponse (
            Long Id,
            String category,
            String question,
            String author,
            LocalDate createDate
    ) {}
}
