package com.kdt.wolf.domain.group.dto;

public class RecruitApplyDto {
    public record RecruitApplyDetail(
            Long recruitApplyId,
            String name,
            String email,
            String position,
            String applicationReason,
            String introduction,
            String techStack,
            String portfolioLink,
            String availableDays,
            String additionalNotes
    ) { }
}
