package com.kdt.wolf.domain.user.dto;

public class SignUpDto {
    public record SignUpRequest(
            String jobTitle,
            String organization,
            int experience,

            String interests,
            String nickname
    ) {
    }
}