package com.kdt.wolf.domain.group.controller;

import com.kdt.wolf.domain.group.dto.request.*;
import com.kdt.wolf.domain.group.dto.response.*;
import com.kdt.wolf.domain.group.service.*;
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
    private final LinkService linkService;
    private final TaskService taskService;


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

    @Operation(summary = "댓글 작성")
    @PostMapping("/{groupId}/question/{questionId}/comment")
    public ApiResult<Void> addComment(
            @PathVariable Long groupId,
            @PathVariable Long questionId,
            @RequestBody QuestionCommentRequest request) {

        questionBoardService.createComment(questionId, request);
        return ApiResult.ok(null);
    }

    @Operation(summary = "대댓글 작성")
    @PostMapping("/{groupId}/question/{questionId}/comment/{parentCommentId}")
    public ApiResult<Void> addComment(
            @PathVariable Long groupId,
            @PathVariable Long questionId,
            @PathVariable Long parentCommentId,
            @RequestBody QuestionCommentRequest request) {

        questionBoardService.createComment(questionId, parentCommentId, request);
        return ApiResult.ok(null);
    }

    @Operation(summary = "댓글 수정")
    @PutMapping("/{groupId}/question/{questionId}/comment/{commentId}")
    public ApiResult<Void> updateComment(
            @PathVariable Long groupId,
            @PathVariable Long questionId,
            @PathVariable Long commentId,
            @RequestBody QuestionCommentRequest request){
        questionBoardService.editComment(commentId, request);
        return ApiResult.ok(null);
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{groupId}/question/{questionId}/comment/{commentId}")
    public ApiResult<Void> deleteComment(
            @PathVariable Long groupId,
            @PathVariable Long questionId,
            @PathVariable Long commentId){
        questionBoardService.deleteComment(commentId);
        return ApiResult.ok(null);
    }

    @Operation(summary = "팀원 평가 작성")
    @PostMapping("/{groupId}/evaluate")
    public ApiResult<Void> postEvaluation(
            @PathVariable Long groupId,
            @RequestBody List<EvaluateRequest> request){
        groupMemberService.addEvaluation(groupId, request);
        return ApiResult.ok(null);
    }

    @Operation(summary = "공유 링크 조회")
    @GetMapping("/{groupId}/links")
    public ApiResult<List<LinkResponse>> getLinks(
            @PathVariable Long groupId){
        List<LinkResponse> response = linkService.getLinks(groupId);
        return ApiResult.ok(response);
    }

    @Operation(summary = "공유 링크 등록")
    @PostMapping("/{groupId}/links")
    public ApiResult<Void> addLinks(
            @PathVariable Long groupId,
            @RequestBody LinkRequest request){
        linkService.addLink(groupId, request);
        return ApiResult.ok(null);
    }

    @Operation(summary = "공유 링크 수정")
    @PutMapping("/{groupId}/links/{linkId}")
    public ApiResult<Void> updateLinks(
            @PathVariable Long groupId,
            @PathVariable Long linkId,
            @RequestBody LinkRequest request){
        linkService.editLink(linkId, request);
        return ApiResult.ok(null);
    }

    @Operation(summary = "공유 링크 삭제")
    @DeleteMapping("/{groupId}/links/{linkId}")
    public ApiResult<Void> deleteLinks(
            @PathVariable Long groupId,
            @PathVariable Long linkId){
        linkService.deleteLink(linkId);
        return ApiResult.ok(null);
    }

    @Operation(summary = "할일 등록")
    @PostMapping("/{groupId}/task")
    public ApiResult<Long> addTask(
            @PathVariable Long groupId,
            @RequestBody TaskRequest request){
        Long taskId = taskService.addTask(groupId, request);
        return ApiResult.ok(taskId);
    }

    @Operation(summary = "할일 조회")
    @GetMapping("/{groupId}/task")
    public ApiResult<List<TaskResponse>> getTask(
            @PathVariable Long groupId){
        List<TaskResponse> responses = taskService.getTask(groupId);
        return ApiResult.ok(responses);
    }

    @Operation(summary = "할일 수정")
    @PutMapping("/{groupId}/task/{taskId}")
    public ApiResult<Void> updateTask(
            @PathVariable Long groupId,
            @PathVariable Long taskId,
            @RequestBody TaskRequest request){
        taskService.editTask(taskId, request);
        return ApiResult.ok(null);
    }

    @Operation(summary = "할일 삭제")
    @DeleteMapping("/{groupId}/task/{taskId}")
    public ApiResult<Void> deleteTask(
            @PathVariable Long groupId,
            @PathVariable Long taskId){
        taskService.deleteTask(taskId);
        return ApiResult.ok(null);
    }
}
