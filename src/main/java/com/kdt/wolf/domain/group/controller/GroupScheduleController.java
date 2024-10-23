package com.kdt.wolf.domain.group.controller;

import com.kdt.wolf.domain.group.dto.request.ScheduleRequest;
import com.kdt.wolf.domain.group.dto.response.ScheduleResponse;
import com.kdt.wolf.domain.group.service.ScheduleService;
import com.kdt.wolf.global.auth.dto.AuthenticatedUser;
import com.kdt.wolf.global.base.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class GroupScheduleController {
    private final ScheduleService scheduleService;

    @Operation(summary = "일정 등록")
    @PostMapping("/{groupId}/schedule")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResult<Long> addSchedule(@PathVariable Long groupId,
                                   @RequestBody ScheduleRequest request,
                                   @AuthenticationPrincipal AuthenticatedUser user ) {
        Long scheduleId = scheduleService.addSchedule(groupId, request, user.getUserId());
        return ApiResult.ok(scheduleId);
    }

    @Operation(summary = "일정 조회")
    @GetMapping("/{groupId}/schedule")
    public ApiResult<List<ScheduleResponse>> getSchedule(@PathVariable Long groupId) {
        List<ScheduleResponse> responses = scheduleService.getSchedule(groupId);
        return ApiResult.ok(responses);
    }

    @Operation(summary = "일정 수정")
    @PutMapping("/schedule/{scheduleId}")
    public ApiResult<Void> updateSchedule( @PathVariable Long scheduleId,
                                       @RequestBody ScheduleRequest request) {
        scheduleService.editSchedule(scheduleId, request);
        return ApiResult.ok(null);
    }

    @Operation(summary = "일정 삭제")
    @DeleteMapping("/schedule/{scheduleId}")
    public ApiResult<Void> deleteSchedule( @PathVariable Long scheduleId ) {
        scheduleService.deleteSchedule(scheduleId);
        return ApiResult.ok(null);
    }
}
