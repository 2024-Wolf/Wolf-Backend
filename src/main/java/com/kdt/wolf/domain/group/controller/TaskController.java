package com.kdt.wolf.domain.group.controller;

import com.kdt.wolf.domain.group.dto.TaskDto;
import com.kdt.wolf.domain.group.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post/{postId}/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getTasks(@PathVariable Long postId) {
        List<TaskDto> tasks = taskService.getTasks(postId);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable Long postId, @PathVariable Long taskId) {
        TaskDto task = taskService.getTask(postId, taskId);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<String> createTask(@PathVariable Long postId, @RequestBody TaskDto taskDto) {
        taskService.createTask(postId, taskDto);
        return ResponseEntity.ok("Task created successfully");
    }

    @PutMapping
    public ResponseEntity<String> updateTask(@PathVariable Long postId, @RequestBody TaskDto taskDto) {
        taskService.updateTask(postId, taskDto);
        return ResponseEntity.ok("Task updated successfully");
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long postId, @PathVariable Long taskId) {
        taskService.deleteTask(postId, taskId);
        return ResponseEntity.ok("Task deleted successfully");
    }
}
