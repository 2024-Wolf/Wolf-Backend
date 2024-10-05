package com.kdt.wolf.domain.challenge.dto;


import com.kdt.wolf.domain.challenge.dto.response.ChallengeStatus;
import java.time.LocalDate;

public class ChallengeDto {
    public record ChallengePreview(
            Long challengePostId,
            String img,
            String title,
            String registrationDate,
            String deadline,
            ChallengeStatus status
    ) {
        public ChallengePreview(Long challengePostId,
                                String img,
                                String title,
                                LocalDate registrationDate,
                                LocalDate deadline) {
            this(challengePostId,
                    img,
                    title,
                    registrationDate.toString(),
                    deadline.toString(),
                    null
            );
        }
    }
}
