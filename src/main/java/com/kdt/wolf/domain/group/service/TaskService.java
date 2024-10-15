package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dao.TaskDao;
import com.kdt.wolf.domain.group.dto.request.TaskRequest;
import com.kdt.wolf.domain.group.dto.response.TaskResponse;
import com.kdt.wolf.domain.group.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskDao taskDao;

    public void addTask(Long groupId, TaskRequest request) {
        taskDao.createTask(groupId, request);
    }

    public List<TaskResponse> getTask(Long groupId) {
        List<TaskEntity> tasks = taskDao.findAllByGroupId(groupId);
        return tasks.stream()
                .map(TaskResponse::new)
                .toList();
    }

    public void editTask(Long taskId, TaskRequest request) {
        taskDao.updateTask(taskId, request);
    }

    public void deleteTask(Long taskId) {
        taskDao.deleteTask(taskId);
    }
}
