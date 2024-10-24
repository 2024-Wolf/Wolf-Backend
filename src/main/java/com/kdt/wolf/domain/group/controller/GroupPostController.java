package com.kdt.wolf.domain.group.controller;

import com.kdt.wolf.domain.group.dto.GroupNewsDto.GroupNews;
import com.kdt.wolf.domain.group.dto.GroupPreviewUserDto.GroupPreviewUserPageResponse;
import com.kdt.wolf.domain.group.dto.request.*;
import com.kdt.wolf.domain.group.dto.response.*;
import com.kdt.wolf.domain.group.entity.common.GroupStatus;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import com.kdt.wolf.domain.group.service.*;
import com.kdt.wolf.global.auth.dto.AuthenticatedUser;
import com.kdt.wolf.global.base.ApiResult;
import com.kdt.wolf.global.util.FileValidationUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class GroupPostController {
    private final GroupPostService groupPostService;
    private final RecruitApplyService recruitApplyService;
    private final GroupMemberService groupMemberService;

    @Operation(summary = "모집글 작성")
    @PostMapping
    public ApiResult<Long> createPost(@RequestBody GroupPostRequest request,
                                      @AuthenticationPrincipal AuthenticatedUser user) {
        Long groupPostId = groupPostService.createPost(request, user.getUserId());
        return ApiResult.ok(groupPostId);
    }

    @Operation(summary = "모집글 썸네일 등록 및 수정")
    @PostMapping(value ="/{postId}/thumbnail", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResult<String> uploadThumbnail(@PathVariable Long postId,
                                             @RequestParam("thumbnailImage") MultipartFile thumbnailImage) {
        FileValidationUtil.validateImageFile(thumbnailImage);
        String thumbnailUrl = groupPostService.uploadThumbnail(postId, thumbnailImage);
        return ApiResult.ok(thumbnailUrl);
    }

    @Operation(summary = "모집글 Type별 View")
    @GetMapping("/view/{type}")
    public ApiResult<GroupPostPageResponse> getPosts(@PathVariable String type,
                                                     @PageableDefault(page = 0, size = 20) Pageable pageable) {
        GroupPostPageResponse responses = groupPostService.getPostsByType(type, pageable);
        return ApiResult.ok(responses);
    }

    @Operation(summary = "모집글 검색")
    @GetMapping("/search/{keyword}")
    public ApiResult<List<GroupPostResponse>> searchPosts(@PathVariable String keyword) {
        List<GroupPostResponse> responses = groupPostService.searchPosts(keyword);
        return ApiResult.ok(responses);
    }

    @Operation(summary = "유저별 그룹 검색")
    @GetMapping("/{type}/{status}")
    public ApiResult<GroupPreviewUserPageResponse> getPostsByUser(@AuthenticationPrincipal AuthenticatedUser user,
                                                                  @PathVariable GroupType type,
                                                                  @PathVariable GroupStatus status,
                                                                  @PageableDefault(size = 20) Pageable pageable) {

        if(status.equals(GroupStatus.APPLYING)) {
            //RecruitApplyDao
            GroupPreviewUserPageResponse response = recruitApplyService.getAppliedGroupsByUserIdAndType(user.getUserId(), type, pageable);
            return ApiResult.ok(response);
        }
        if(status.equals(GroupStatus.ONGOING)) {
            //GroupMemberDao
            GroupPreviewUserPageResponse response = groupMemberService.getOngoingPostsByUserIdAndType(user.getUserId(), type, pageable);
            return ApiResult.ok(response);
        }
        if(status.equals(GroupStatus.COMPLETED)) {
            //GroupMemberDao
            GroupPreviewUserPageResponse response = groupMemberService.getCompletedPostsByUserIdAndType(user.getUserId(), type, pageable);
            return ApiResult.ok(response);
        }
        throw new IllegalArgumentException("잘못된 status 값입니다.");
    }

    @Operation(summary = "그룹 정보 조회")
    @GetMapping("/{postId}")
    public ApiResult<GroupPostResponse> getGroupPost(@PathVariable Long postId) {
        GroupPostResponse groupPostResponse = groupPostService.getGroupPostById(postId);
        return ApiResult.ok(groupPostResponse);
    }

    @Operation(summary = "그룹 정보 수정")
    @PutMapping("/{postId}")
    public ApiResult<Void> updateGroupPost( @PathVariable Long postId,
                                            @RequestBody GroupPostRequest request,
                                            @AuthenticationPrincipal AuthenticatedUser user) {
        groupPostService.editGroupPost(postId, request, user.getUserId());
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

    @Operation(summary = "모임 탈퇴")
    @GetMapping("/{groupId}/members/{userId}")
    public ApiResult<Void> deleteGroupMembers(@PathVariable Long groupId, @PathVariable Long userId) {
        groupMemberService.removeGroupMembers(groupId, userId);
        return ApiResult.ok(null);
    }

    @Operation(summary = "모임장 변경")
    @PutMapping("/{groupId}/members/role/{memberId}")
    public ApiResult<Void> updateGroupMembers(@PathVariable Long groupId, @PathVariable Long memberId) {
        groupMemberService.updateGroupMembers(groupId, memberId);
        return ApiResult.ok(null);
    }

    @Operation(summary = "팀원 평가 작성")
    @PostMapping("/{groupId}/evaluate")
    public ApiResult<Void> postEvaluation(@PathVariable Long groupId,
                                          @RequestBody List<EvaluateRequest> request){
        groupMemberService.addEvaluation(groupId, request);
        return ApiResult.ok(null);
    }

    @Operation(summary = "최신 소식 조회")
    @GetMapping("/{groupId}/news")
    public ApiResult<List<GroupNews>> getGroupNews(@PathVariable Long groupId) {
        List<GroupNews> responses = groupPostService.getGroupNews(groupId);
        return ApiResult.ok(responses);
    }
}
