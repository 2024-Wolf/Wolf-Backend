package com.kdt.wolf.domain.group.dao;

import com.kdt.wolf.domain.group.dto.request.ScheduleRequest;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.ScheduleEntity;
import com.kdt.wolf.domain.group.repository.GroupPostRepository;
import com.kdt.wolf.domain.group.repository.ScheduleRepository;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.UserRepository;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduleDao {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final GroupPostRepository groupPostRepository;

    public Long createSchedule(Long groupId, ScheduleRequest request, Long userId) {
        UserEntity author = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        GroupPostEntity group = groupPostRepository.findById(groupId)
                .orElseThrow(NotFoundException::new);

        ScheduleEntity schedule = ScheduleEntity.builder()
                .groupPost(group)
                .user(author)
                .details(request.getDetails())
                .startTime(request.getStartDate())
                .endTime(request.getEndDate())
                .build();
        ScheduleEntity savedSchedule = scheduleRepository.save(schedule);
        return savedSchedule.getScheduleId();
    }

    public List<ScheduleEntity> findAllByGroupId(Long groupId) {
        GroupPostEntity groupPost = groupPostRepository.findById(groupId)
                .orElseThrow(NotFoundException::new);
        return scheduleRepository.findAllByGroupPost(groupPost);
    }

    public void updateSchedule(Long scheduleId, ScheduleRequest request) {
        ScheduleEntity scheduleEntity = scheduleRepository.findById(scheduleId)
                .orElseThrow(NotFoundException::new);
        scheduleEntity.updateSchedule(request);
        scheduleRepository.save(scheduleEntity);
    }

    public void deleteSchedule(Long scheduleId) {
        if(!scheduleRepository.existsById(scheduleId)) {
            throw new NotFoundException();
        }
        scheduleRepository.deleteById(scheduleId);
    }
}
