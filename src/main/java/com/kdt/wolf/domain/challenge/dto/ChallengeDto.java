package com.kdt.wolf.domain.challenge.dto;


import com.kdt.wolf.global.dto.PageResponse;
import java.time.LocalDate;

import com.kdt.wolf.domain.challenge.entity.ChallengePostEntity;
import java.util.List;
import lombok.Getter;

public class ChallengeDto {
    @Getter
    public static class ChallengePreview {
        private final Long challengePostId;
        private final String img;
        private final String title;
        private final String registrationDate;
        private final String deadline;
        private final ChallengeStatus status;

        public ChallengePreview(Long challengePostId, String img, String title, LocalDate registrationDate, LocalDate deadline, ChallengeStatus status) {
            this.challengePostId = challengePostId;
            this.img = img;
            this.title = title;
            this.registrationDate = registrationDate.toString();
            this.deadline = deadline.toString();
            this.status = status;
        }
    }

    public record ChallengePageResponse(
            List<ChallengePreview> challenges,
            PageResponse page
    ) { }
}
