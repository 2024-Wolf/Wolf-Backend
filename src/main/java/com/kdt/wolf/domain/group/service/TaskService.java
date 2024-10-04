package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dto.TaskDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    public List<TaskDto> getTasks(Long postId) {
        return new ArrayList<>();
    }

    public TaskDto getTask(Long postId, Long taskId) {
        return new TaskDto(taskId, "Sample Task", "Description", false);
    }

    public void createTask(Long postId, TaskDto taskDto) {
    }

    public void updateTask(Long postId, TaskDto taskDto) {
    }

    public void deleteTask(Long postId, Long taskId) {
    }
}
