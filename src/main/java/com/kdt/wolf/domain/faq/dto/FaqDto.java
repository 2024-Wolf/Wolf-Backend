package com.kdt.wolf.domain.faq.dto;

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
}
