package com.kdt.wolf.domain.group.dto.request;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecruitApplyRequest {
    @NotNull
    private String position;

    @NotNull
    private String email;

    @NotNull
    private String applicationReason;

    private String introduction;
    private String techStack;
    private String portfolioLink;
    private String availableDays;
    private String additionalNotes;
}
