package com.kdt.wolf.domain.group.dto.response;

import com.kdt.wolf.domain.group.entity.ScheduleEntity;
import lombok.Getter;

@Getter
public class ScheduleResponse {
    private final Long id;
    private final String details;
    private final String startTime;
    private final String endTime;
    public ScheduleResponse(ScheduleEntity scheduleEntity) {
        this.id = scheduleEntity.getScheduleId();
        this.details = scheduleEntity.getDetails();
        this.startTime = scheduleEntity.getStartTime().toString();
        this.endTime = scheduleEntity.getEndTime().toString();
    }
}
