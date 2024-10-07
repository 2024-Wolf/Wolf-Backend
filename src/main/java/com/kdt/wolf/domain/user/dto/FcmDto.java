package com.kdt.wolf.domain.user.dto;

import java.time.LocalDateTime;

public class FcmDto {
    public record FcmSendDto(String token, String title, String body) { }


    public record FcmMessageDto(
            boolean validate_only,
            Message message
    ) {}

    public record Message(
            Notification notification,
            String token
    ) {}

    public record Notification(
            String title,
            String body
    ) {}

    public record AlertDto(
            String alertContent,
            String alertLink,
            LocalDateTime alertTime
    ) { }
}
