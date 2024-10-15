package com.kdt.wolf.domain.group.dao;

import com.kdt.wolf.domain.group.dto.request.TaskRequest;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.TaskEntity;
import com.kdt.wolf.domain.group.entity.common.TaskStatus;
import com.kdt.wolf.domain.group.repository.GroupPostRepository;
import com.kdt.wolf.domain.group.repository.TaskRepository;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.UserRepository;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskDao {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final GroupPostRepository groupPostRepository;

    public Long createTask(Long groupId, TaskRequest request) {
        UserEntity author = userRepository.findById(request.getAuthorId())
                .orElseThrow(UserNotFoundException::new);
        GroupPostEntity group = groupPostRepository.findById(groupId)
                .orElseThrow(NotFoundException::new);

        TaskEntity task = TaskEntity.builder()
                .groupPost(group)
                .user(author)
                .details(request.getDetails())
                .status(TaskStatus.valueOf(request.getStatus().toUpperCase()))
                .build();
        TaskEntity savedTask = taskRepository.save(task);
        return savedTask.getTaskId();
    }

    public List<TaskEntity> findAllByGroupId(Long groupId) {
        GroupPostEntity groupPost = groupPostRepository.findById(groupId)
                .orElseThrow(NotFoundException::new);
        return taskRepository.findAllByGroupPost(groupPost);
    }

    public void updateTask(Long taskId, TaskRequest request) {
        TaskEntity taskEntity = taskRepository.findById(taskId)
                .orElseThrow(NotFoundException::new);
        taskEntity.updateTask(request);
        taskRepository.save(taskEntity);
    }

    public void deleteTask(Long taskId) {
        if(!taskRepository.existsById(taskId)) {
            throw new NotFoundException();
        }
        taskRepository.deleteById(taskId);
    }
}
