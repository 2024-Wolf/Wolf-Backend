package com.kdt.wolf.global.fcm.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FCMNotificationRequestDto {
    private Long targetUserId;
    private String title;
    private String body;
    private String link;

    @Builder
    public FCMNotificationRequestDto(Long targetUserId, String title, String body, String link) {
        this.targetUserId = targetUserId;
        this.title = title;
        this.body = body;
        this.link = link;
    }

}
