package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dao.GroupPostDao;
import com.kdt.wolf.domain.group.dao.ScheduleDao;
import com.kdt.wolf.domain.group.dto.request.ScheduleRequest;
import com.kdt.wolf.domain.group.dto.response.ScheduleResponse;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.ScheduleEntity;
import com.kdt.wolf.domain.group.entity.common.GroupNewsActionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleDao scheduleDao;
    private final GroupPostDao groupPostDao;
    private final GroupNewsService groupNewsService;
    public Long addSchedule(Long groupId, ScheduleRequest request, Long userId) {
        GroupPostEntity group = groupPostDao.findById(groupId);
        Long scheduleId =  scheduleDao.createSchedule(group, request, userId);
        groupNewsService.createGroupNews(group, request.getDetails() +  GroupNewsActionType.ADD_SCHEDULE);
        return scheduleId;
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
