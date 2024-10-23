package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dao.ScheduleDao;
import com.kdt.wolf.domain.group.dto.request.ScheduleRequest;
import com.kdt.wolf.domain.group.dto.response.ScheduleResponse;
import com.kdt.wolf.domain.group.entity.ScheduleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleDao scheduleDao;
    public Long addSchedule(Long groupId, ScheduleRequest request, Long userId) {
        return scheduleDao.createSchedule(groupId, request, userId);
    }

    public List<ScheduleResponse> getSchedule(Long groupId) {
        List<ScheduleEntity> schedules = scheduleDao.findAllByGroupId(groupId);
        return schedules.stream()
                .map(ScheduleResponse::new)
                .toList();
    }

    public void editSchedule(Long scheduleId, ScheduleRequest request) {
        scheduleDao.updateSchedule(scheduleId, request);
    }

    public void deleteSchedule(Long scheduleId) {
        scheduleDao.deleteSchedule(scheduleId);
    }
}
