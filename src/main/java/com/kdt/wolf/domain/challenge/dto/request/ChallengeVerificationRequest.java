package com.kdt.wolf.domain.challenge.dto.request;

import lombok.Getter;

@Getter
public class ChallengeVerificationRequest {
    private Long challengePostId;
    private Long groupPostId;
    private String status;

    private String certificationNo;
    private String institutionName;
    private String verificationContent;

}
