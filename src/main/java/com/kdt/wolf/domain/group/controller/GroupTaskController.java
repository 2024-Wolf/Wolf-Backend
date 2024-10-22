package com.kdt.wolf.domain.group.controller;

import com.kdt.wolf.domain.group.dto.request.TaskRequest;
import com.kdt.wolf.domain.group.dto.response.TaskResponse;
import com.kdt.wolf.domain.group.service.GroupMemberService;
import com.kdt.wolf.domain.group.service.GroupPostService;
import com.kdt.wolf.domain.group.service.RecruitApplyService;
import com.kdt.wolf.domain.group.service.TaskService;
import com.kdt.wolf.global.auth.dto.AuthenticatedUser;
import com.kdt.wolf.global.base.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class GroupTaskController {
    private final TaskService taskService;

    @Operation(summary = "할일 등록")
    @PostMapping("/{groupId}/task")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResult<Long> addTask( @PathVariable Long groupId,
                                    @RequestBody TaskRequest request,
                                    @AuthenticationPrincipal AuthenticatedUser user ) {
        Long taskId = taskService.addTask(groupId, request, user.getUserId());
        return ApiResult.ok(taskId);
    }

    @Operation(summary = "할일 조회 / status : NOT_STARTED, IN_PROGRESS, COMPLETED")
    @GetMapping("/{groupId}/task")
    public ApiResult<List<TaskResponse>> getTask( @PathVariable Long groupId ) {
        List<TaskResponse> responses = taskService.getTask(groupId);
        return ApiResult.ok(responses);
    }

    @Operation(summary = "할일 수정")
    @PutMapping("/task/{taskId}")
    public ApiResult<Void> updateTask( @PathVariable Long taskId,
                                       @RequestBody TaskRequest request) {
        taskService.editTask(taskId, request);
        return ApiResult.ok(null);
    }

    @Operation(summary = "할일 삭제")
    @DeleteMapping("/task/{taskId}")
    public ApiResult<Void> deleteTask( @PathVariable Long taskId ) {
        taskService.deleteTask(taskId);
        return ApiResult.ok(null);
    }
}
