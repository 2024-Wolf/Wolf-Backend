package com.kdt.wolf.domain.challenge.dto;

import lombok.Getter;

@Getter
public enum ChallengeStatus {
    CERTIFICATION("CERTIFICATION", "인증하기"),
    CERTIFICATION_COMPLETE("CERTIFICATION_COMPLETE", "인증완료"),
    RESULT_CONFIRM("RESULT_CONFIRM", "결과 확인"),
    APPLY("APPLY", "신청하기"),
    PARTICIPATE("PARTICIPATE", "참여하기"),
    PAY("PAY", "결제하기");
    private final String status;
    private final String description;

    ChallengeStatus(String status, String description) {
        this.status = status;
        this.description = description;
    }
}