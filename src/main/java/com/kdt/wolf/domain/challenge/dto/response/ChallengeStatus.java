package com.kdt.wolf.domain.challenge.dto.response;

import lombok.Getter;

@Getter
public enum ChallengeStatus {
    CERTIFICATION("CERTIFICATION", "인증하기"),
    CERTIFICATION_COMPLETE("CERTIFICATION_COMPLETE", "인증완료"),
    RESULT_CONFIRM("RESULT_CONFIRM", "결과 확인"),
    APPLY("APPLY", "신청하기"),
    GROUP_LEADER_ONLY("GROUP_LEADER_ONLY", "그룹장만 신청 가능"),
    PARTICIPATE("PARTICIPATE", "참여하기");

    private final String status;
    private final String description;

    ChallengeStatus(String status, String description) {
        this.status = status;
        this.description = description;
    }
}
