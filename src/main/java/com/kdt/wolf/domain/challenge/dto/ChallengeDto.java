package com.kdt.wolf.domain.challenge.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

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
        public ChallengePreview(Long challengePostId,
                                String img,
                                String title,
                                LocalDateTime registrationDate,
                                LocalDate deadline) {
            this(challengePostId,
                    img,
                    title,
                    registrationDate.toString(),
                    deadline.toString(),
                    null
            );
        }

        public ChallengePreview (ChallengePreview challengePreview ,ChallengeStatus status) {
            this(challengePreview.challengePostId(),
                    challengePreview.img(),
                    challengePreview.title(),
                    challengePreview.registrationDate(),
                    challengePreview.deadline(),
                    status
            );
        }
    }
}
