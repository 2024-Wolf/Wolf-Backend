package com.kdt.wolf.domain.challenge.dto.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ChallengeCreationRequest {
    private String img;
    private String title;
    private String content;
    private String manner;
    private String awardContent;
    private LocalDate deadline;
}
