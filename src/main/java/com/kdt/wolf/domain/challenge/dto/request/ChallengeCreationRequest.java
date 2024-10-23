package com.kdt.wolf.domain.challenge.dto.request;

import lombok.Getter;

import java.time.LocalDate;
import org.springframework.web.multipart.MultipartFile;

public class ChallengeCreationRequest {
    public record ChallengeCreateRequest(
             MultipartFile img,
             String title,
             String content,
             String manner,
             String awardContent,
             LocalDate registrationDate,
             LocalDate deadline
    ){}

}
