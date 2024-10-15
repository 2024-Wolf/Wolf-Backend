package com.kdt.wolf.domain.group.controller;

import com.kdt.wolf.domain.group.dto.request.*;
import com.kdt.wolf.domain.group.dto.response.GroupMemberResponse;
import com.kdt.wolf.domain.group.dto.response.GroupPostPageResponse;
import com.kdt.wolf.domain.group.dto.response.GroupPostResponse;
import com.kdt.wolf.domain.group.dto.response.LinkResponse;
import com.kdt.wolf.domain.group.entity.common.GroupStatus;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import com.kdt.wolf.domain.group.service.*;
import com.kdt.wolf.global.auth.dto.AuthenticatedUser;
import com.kdt.wolf.global.base.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class GroupPostController {
    private final GroupPostService groupPostService;
    private final RecruitApplyService recruitApplyService;
    private final GroupMemberService groupMemberService;
    private final LinkService linkService;


    @Operation(summary = "모집글 작성")
    @PostMapping
    public ApiResult<Void> createPost(@RequestBody GroupPostRequest request) {
        groupPostService.createPost(request);
        return ApiResult.ok(null);
    }

    @Operation(summary = "모집글 Type별 View")
    @GetMapping("/{option}")
    public ApiResult<GroupPostPageResponse> getPosts(@PathVariable String option,
                                                     @PageableDefault(page = 0, size = 20) Pageable pageable) {
        GroupPostPageResponse responses = groupPostService.getPostsByOption(option, pageable);
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
    public ApiResult<GroupPostPageResponse> getPostsByUser( @AuthenticationPrincipal AuthenticatedUser user,
                                                            @PathVariable GroupType type,
                                                            @PathVariable GroupStatus status,
                                                            @PageableDefault(size = 20) Pageable pageable) {



        if(status.equals(GroupStatus.APPLYING)) {
            //RecruitApplyDao
            GroupPostPageResponse response = recruitApplyService.getAppliedGroupsByUserIdAndType(user.getUserId(), type, pageable);
            return ApiResult.ok(response);
        }
        if(status.equals(GroupStatus.ONGOING)) {
            //GroupMemberDao
            GroupPostPageResponse response = groupMemberService.getOngoingPostsByUserIdAndType(user.getUserId(), type, pageable);
            return ApiResult.ok(response);
        }
        if(status.equals(GroupStatus.COMPLETED)) {
            //GroupMemberDao
            GroupPostPageResponse response = groupMemberService.getCompletedPostsByUserIdAndType(user.getUserId(), type, pageable);
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

    @Operation(summary = "팀원 평가 작성")
    @PostMapping("/{groupId}/evaluate")
    public ApiResult<Void> postEvaluation(
            @PathVariable Long groupId,
            @RequestBody List<EvaluateRequest> request){
        groupMemberService.addEvaluation(groupId, request);
        return ApiResult.ok(null);
    }

    @Operation(summary = "외부 링크 조회")
    @GetMapping("/{groupId}/links")
    public ApiResult<List<LinkResponse>> getLinks(
            @PathVariable Long groupId){
        List<LinkResponse> response = linkService.getLinks(groupId);
        return ApiResult.ok(response);
    }
}
