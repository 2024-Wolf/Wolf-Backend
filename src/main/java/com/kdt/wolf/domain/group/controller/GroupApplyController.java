package com.kdt.wolf.domain.group.controller;

import com.kdt.wolf.domain.group.dto.RecruitApplyDto.RecruitApplyDetail;
import com.kdt.wolf.domain.group.dto.request.RecruitApplyRequest;
import com.kdt.wolf.domain.group.service.RecruitApplyService;
import com.kdt.wolf.global.base.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class GroupApplyController {
    private final RecruitApplyService recruitApplyService;

//    @Operation(summary = "그룹 지원")
//    @PostMapping("/{groupId}/apply")
//    public ApiResult<Void> applyToGroup(
//            @PathVariable Long groupId,
//            @RequestParam Long userId,
//            @RequestBody RecruitApplyRequest request) {
//        recruitApplyService.recruitApply(groupId, userId, request);
//        return ApiResult.ok(null);
//    }

    @Operation(summary = "그룹 지원서 확인")
    @PostMapping("/apply/{recruitApplyId}")
    public ApiResult<RecruitApplyDetail> getApplicationsById(@PathVariable Long recruitApplyId) {
        RecruitApplyDetail response = recruitApplyService.getApplicationsById(recruitApplyId);
        return ApiResult.ok(response);
    }

}
