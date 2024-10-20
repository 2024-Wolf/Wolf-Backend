package com.kdt.wolf.domain.alert.dto;

import com.kdt.wolf.domain.alert.entity.AlertType;
import com.kdt.wolf.global.fcm.service.dto.FCMNotificationRequestDto;
import java.time.LocalDateTime;

public class AlertDto {

    public record AlertResponse(
            String alertType,
            String alertContent,
            String alertLink,
            LocalDateTime alertTime
    ) { }

    public record AlertRequest(
            Long targetUserId,
            AlertType alertType,
            String alertContent,
            String alertLink
    ) {
        public FCMNotificationRequestDto toFCMNotificationRequestDto() {
            return new FCMNotificationRequestDto(
                    targetUserId,
                    alertType.getType(),
                    alertContent,
                    alertLink);
        }
    }
}
