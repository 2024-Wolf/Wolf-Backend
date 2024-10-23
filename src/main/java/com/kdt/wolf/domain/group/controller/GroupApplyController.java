package com.kdt.wolf.domain.group.controller;

import com.kdt.wolf.domain.group.dto.RecruitApplyDto.ApplicationsMember;
import com.kdt.wolf.domain.group.dto.RecruitApplyDto.RecruitApplyDetail;
import com.kdt.wolf.domain.group.dto.request.RecruitApplyRequest;
import com.kdt.wolf.domain.group.entity.common.ApplyStatus;
import com.kdt.wolf.domain.group.service.RecruitApplyService;
import com.kdt.wolf.global.auth.dto.AuthenticatedUser;
import com.kdt.wolf.global.base.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post/apply")
public class GroupApplyController {
    private final RecruitApplyService recruitApplyService;

    @Operation(summary = "그룹 지원")
    @PostMapping("/{groupId}")
    public ApiResult<Void> applyToGroup(
            @PathVariable Long groupId,
            @AuthenticationPrincipal AuthenticatedUser user,
            @RequestBody RecruitApplyRequest request) {
        recruitApplyService.recruitApply(groupId, user.getUserId(), request);
        return ApiResult.ok(null);
    }

    @Operation(summary = "그룹 지원서 상세 정보 확인")
    @GetMapping("/detail/{recruitApplyId}")
    public ApiResult<RecruitApplyDetail> getApplicationsById(@PathVariable Long recruitApplyId) {
        RecruitApplyDetail response = recruitApplyService.getApplicationsById(recruitApplyId);
        return ApiResult.ok(response);
    }

    @Operation(summary = "그룹 지원 리스트")
    @GetMapping("/{groupId}")
    public ApiResult<List<ApplicationsMember>> getPendingApplicationsByGroupId(@PathVariable Long groupId) {
        List<ApplicationsMember> response = recruitApplyService.getPendingApplicationsByGroupId(groupId);
        return ApiResult.ok(response);
    }

    @Operation(summary = "그룹 지원서 상태 변경 / status : ACCEPTED, REJECTED")
    @GetMapping("/{recruitApplyId}/{status}")
    public ApiResult<?> changeApplicationStatus(@PathVariable Long recruitApplyId, @PathVariable ApplyStatus status) {
        recruitApplyService.changeApplicationStatus(recruitApplyId, status);
        return ApiResult.ok(null);
    }
}
