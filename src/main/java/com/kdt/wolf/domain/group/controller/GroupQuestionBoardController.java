package com.kdt.wolf.domain.group.controller;

import com.kdt.wolf.domain.group.dto.request.QuestionCommentRequest;
import com.kdt.wolf.domain.group.dto.request.QuestionRequest;
import com.kdt.wolf.domain.group.dto.response.QuestionPageResponse;
import com.kdt.wolf.domain.group.service.QuestionBoardService;
import com.kdt.wolf.global.base.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class GroupQuestionBoardController {
    private final QuestionBoardService questionBoardService;


    @Operation(summary = "질문 목록 조회")
    @GetMapping("/{groupId}/question/{option}")
    public ApiResult<QuestionPageResponse> getQuestionsWithComments(
            @PathVariable Long groupId,
            @PathVariable String option,
            @PageableDefault(page = 0, size = 20) Pageable pageable) {
        QuestionPageResponse questions = questionBoardService.getQuestions(groupId, option, pageable);
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
}
