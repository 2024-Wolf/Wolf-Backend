package com.kdt.wolf.domain.alert.dto;

import java.time.LocalDateTime;

public class AlertDto {

    public record AlertResponse(
            String alertContent,
            String alertLink,
            LocalDateTime alertTime
    ) { }
}
