package com.kdt.wolf.domain.group.controller;

import com.kdt.wolf.domain.group.dto.request.QuestionCommentRequest;
import com.kdt.wolf.domain.group.dto.request.QuestionRequest;
import com.kdt.wolf.domain.group.dto.response.QuestionPageResponse;
import com.kdt.wolf.domain.group.service.QuestionBoardService;
import com.kdt.wolf.global.auth.dto.AuthenticatedUser;
import com.kdt.wolf.global.base.ApiResult;
import com.kdt.wolf.global.util.FileValidationUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public ApiResult<Long> registerQuestion(@PathVariable Long groupId,
                                            @PathVariable String option,
                                            @RequestBody QuestionRequest questionRequest,
                                            @AuthenticationPrincipal AuthenticatedUser user) {
        Long questionId = questionBoardService.insertQuestion(groupId, option, questionRequest, user.getUserId());
        return ApiResult.ok(questionId);
    }

    @Operation(summary = "질문 사진 등록 및 수정")
    @PostMapping(value = "/{groupId}/question/{questionId}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResult<String> uploadQuestionImage(@PathVariable Long groupId,
                                                 @PathVariable Long questionId,
                                                 @RequestBody MultipartFile questionImage) {
        FileValidationUtil.validateImageFile(questionImage);
        String questionImageUrl = questionBoardService.uploadQuestionImage(questionId, questionImage);
        return ApiResult.ok(questionImageUrl);
    }

    @Operation(summary = "질문 수정")
    @PutMapping("/{groupId}/question/{questionId}")
    public ApiResult<Void> updateQuestion(
            @PathVariable Long groupId,
            @PathVariable Long questionId,
            @RequestBody QuestionRequest updateRequest,
            @AuthenticationPrincipal AuthenticatedUser user) {

        questionBoardService.editQuestion(questionId, updateRequest, user.getUserId());
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
            @RequestBody QuestionCommentRequest request,
            @AuthenticationPrincipal AuthenticatedUser user) {

        questionBoardService.createComment(questionId, request, user.getUserId());
        return ApiResult.ok(null);
    }

    @Operation(summary = "대댓글 작성")
    @PostMapping("/{groupId}/question/{questionId}/comment/{parentCommentId}")
    public ApiResult<Void> addComment(
            @PathVariable Long groupId,
            @PathVariable Long questionId,
            @PathVariable Long parentCommentId,
            @RequestBody QuestionCommentRequest request,
            @AuthenticationPrincipal AuthenticatedUser user) {

        questionBoardService.createComment(questionId, parentCommentId, request, user.getUserId());
        return ApiResult.ok(null);
    }

    @Operation(summary = "댓글 수정")
    @PutMapping("/{groupId}/question/{questionId}/comment/{commentId}")
    public ApiResult<Void> updateComment( @PathVariable Long groupId,
                                          @PathVariable Long questionId,
                                          @PathVariable Long commentId,
                                          @RequestBody QuestionCommentRequest request,
                                          @AuthenticationPrincipal AuthenticatedUser user){
        questionBoardService.editComment(commentId, request);
        return ApiResult.ok(null);
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{groupId}/question/{questionId}/comment/{commentId}")
    public ApiResult<Void> deleteComment( @PathVariable Long groupId,
                                          @PathVariable Long questionId,
                                          @PathVariable Long commentId,
                                          @AuthenticationPrincipal AuthenticatedUser user){
        questionBoardService.deleteComment(commentId);
        return ApiResult.ok(null);
    }
}
