package com.kdt.wolf.domain.group.controller;

import com.kdt.wolf.domain.group.dto.request.QuestionRequest;
import com.kdt.wolf.domain.group.dto.request.RecruitApplyRequest;
import com.kdt.wolf.domain.group.dto.response.GroupMemberResponse;
import com.kdt.wolf.domain.group.dto.response.GroupPostResponse;
import com.kdt.wolf.domain.group.dto.request.GroupPostRequest;
import com.kdt.wolf.domain.group.dto.response.QuestionResponse;
import com.kdt.wolf.domain.group.service.GroupMemberService;
import com.kdt.wolf.domain.group.service.GroupPostService;
import com.kdt.wolf.domain.group.service.QuestionBoardService;
import com.kdt.wolf.domain.group.service.RecruitApplyService;
import com.kdt.wolf.global.base.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class GroupPostController {

    private final GroupPostService groupPostService;
    private final RecruitApplyService recruitApplyService;
    private final GroupMemberService groupMemberService;
    private final QuestionBoardService questionBoardService;


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

    @Operation(summary = "모임원 조회")
    @GetMapping("/{groupId}/members")
    public ApiResult<List<GroupMemberResponse>> getGroupMembers(@PathVariable Long groupId) {
        List<GroupMemberResponse> members = groupMemberService.getGroupMembers(groupId);
        return ApiResult.ok(members);
    }

    @Operation(summary = "질문 목록 조회")
    @GetMapping("/{groupId}/question/{option}")
    public ApiResult<List<QuestionResponse>> getQuestionsWithComments(
            @PathVariable Long groupId,
            @PathVariable String option) {
        List<QuestionResponse> questions = questionBoardService.getQuestionList(groupId, option);
        return ApiResult.ok(questions);
    }

    @Operation(summary = "질문 등록")
    @PostMapping("/{groupId}/question/{option}")
    public ApiResult<Void> registerQuestion(@PathVariable Long groupId,
                                              @PathVariable String option,
                                              @RequestBody QuestionRequest questionRequest) {
        questionBoardService.insertQuestion(groupId, option, questionRequest);
        return ApiResult.ok(null);
    }

    @Operation(summary = "질문 수정")
    @PutMapping("/{groupId}/question/{questionId}")
    public ApiResult<Void> updateQuestion(
            @PathVariable Long groupId,
            @PathVariable Long questionId,
            @RequestBody QuestionRequest updateRequest) {

        questionBoardService.editQuestion(questionId, updateRequest);
        return ApiResult.ok(null);
    }

    @Operation(summary = "질문 삭제")
    @DeleteMapping("/{groupId}/question/{questionId}")
    public ApiResult<Void> deleteQuestion(
            @PathVariable Long groupId,
            @PathVariable Long questionId) {

        questionBoardService.deleteQuestion(groupId, questionId);
        return ApiResult.ok(null);
    }
}
