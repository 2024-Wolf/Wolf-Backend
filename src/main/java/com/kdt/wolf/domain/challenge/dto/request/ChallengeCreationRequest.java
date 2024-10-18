package com.kdt.wolf.domain.challenge.dto.request;

import lombok.Getter;

import java.time.LocalDate;

public class ChallengeCreationRequest {
    public record ChallengeCreateRequest(
             String img,
             String title,
             String content,
             String manner,
             String awardContent,
             LocalDate registrationDate,
             LocalDate deadline
    ){}

}
