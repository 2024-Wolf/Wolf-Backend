package com.kdt.wolf.domain.group.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ScheduleRequest {
    private String details;
    private LocalDate startDate;
    private LocalDate endDate;
}
