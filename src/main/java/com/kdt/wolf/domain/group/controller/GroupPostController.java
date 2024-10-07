package com.kdt.wolf.domain.group.controller;

import com.kdt.wolf.domain.group.dto.request.RecruitApplyRequest;
import com.kdt.wolf.domain.group.dto.response.GroupPostResponse;
import com.kdt.wolf.domain.group.dto.request.GroupPostRequest; // 추가: 요청 DTO
import com.kdt.wolf.domain.group.service.GroupPostService;
import com.kdt.wolf.domain.group.service.RecruitApplyService;
import com.kdt.wolf.global.base.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class GroupPostController {

    private final GroupPostService groupPostService;
    private final RecruitApplyService recruitApplyService;

    public GroupPostController(GroupPostService groupPostService, RecruitApplyService recruitApplyService) {
        this.groupPostService = groupPostService;
        this.recruitApplyService = recruitApplyService;
    }


    @Operation(summary = "모집글 작성")
    @PostMapping
    public ApiResult<Void> createPost(@RequestBody GroupPostRequest request) {
        groupPostService.createPost(request);
        return ApiResult.ok(null);
    }

    @Operation(summary = "모집글 Type별 View")
    @GetMapping("/{option}")
    public ApiResult<List<GroupPostResponse>> getPosts(@PathVariable String option) {
        List<GroupPostResponse> responses = groupPostService.getPostsByOption(option);
        return ApiResult.ok(responses);
    }

    @Operation(summary = "모집글 검색")
    @GetMapping("/search/{keyword}")
    public ApiResult<List<GroupPostResponse>> searchPosts(@PathVariable String keyword) {
        List<GroupPostResponse> responses = groupPostService.searchPosts(keyword);
        return ApiResult.ok(responses);
    }

    @Operation(summary = "그룹 정보 조회")
    @GetMapping("/{postId}")
    public ApiResult<GroupPostResponse> getGroupPost(@PathVariable Long postId) {
        GroupPostResponse groupPostResponse = groupPostService.getGroupPostById(postId);
        return ApiResult.ok(groupPostResponse);
    }

    @Operation(summary = "그룹 정보 수정")
    @PutMapping("/{postId}")
    public ApiResult<Void> updateGroupPost(
            @PathVariable Long postId,
            @RequestBody GroupPostRequest request) {
        groupPostService.editGroupPost(postId, request);
        return ApiResult.ok(null);
    }

    @Operation(summary = "그룹 정보 삭제")
    @DeleteMapping("/{postId}")
    public ApiResult<Void> deleteGroupPost(@PathVariable Long postId) {
        groupPostService.deleteGroupPost(postId);
        return ApiResult.ok(null);
    }

    @Operation(summary = "그룹 지원")
    @PostMapping("/{groupId}/apply")
    public ApiResult<Void> applyToGroup(
            @PathVariable Long groupId,
            @RequestParam Long userId,
            @RequestBody RecruitApplyRequest request) {
        recruitApplyService.recruitApply(groupId, userId, request);
        return ApiResult.ok(null);
    }
}
