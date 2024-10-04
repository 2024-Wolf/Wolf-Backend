package com.kdt.wolf.domain.group.controller;

import com.kdt.wolf.domain.group.dto.response.GroupPostResponse;
import com.kdt.wolf.domain.group.dto.request.GroupPostRequest; // 추가: 요청 DTO
import com.kdt.wolf.domain.group.service.GroupPostService;
import com.kdt.wolf.global.base.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class GroupPostController {

    private final GroupPostService groupPostService;

    public GroupPostController(GroupPostService groupPostService) {
        this.groupPostService = groupPostService;
    }

    @Operation(summary = "모집글 작성")
    @PostMapping
    public ApiResult<Void> createPost(@RequestBody GroupPostRequest request) {
        groupPostService.createPost(request);
        return ApiResult.ok(null);
    }

    @Operation(summary = "모집글 조회")
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
}
