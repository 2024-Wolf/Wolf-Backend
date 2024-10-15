package com.kdt.wolf.domain.link.controller;


import com.kdt.wolf.domain.link.dto.LinkRequest;
import com.kdt.wolf.domain.link.dto.LinkResponse;
import com.kdt.wolf.domain.link.service.LinkService;
import com.kdt.wolf.global.base.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/v1")
public class LinkController {
    private final LinkService linkService;

    @Operation(summary = "공유 링크 조회")
    @GetMapping("/post/{groupId}/links")
    public ApiResult<List<LinkResponse>> getLinks(
            @PathVariable Long groupId){
        List<LinkResponse> response = linkService.getLinks(groupId);
        return ApiResult.ok(response);
    }

    @Operation(summary = "공유 링크 등록")
    @PostMapping("/post/{groupId}/links")
    public ApiResult<Void> addLinks(
            @PathVariable Long groupId,
            @RequestBody LinkRequest request){
        linkService.addLink(groupId, request);
        return ApiResult.ok(null);
    }

    @Operation(summary = "공유 링크 수정")
    @PutMapping("/post/{groupId}/links/{linkId}")
    public ApiResult<Void> updateLinks(
            @PathVariable Long groupId,
            @PathVariable Long linkId,
            @RequestBody LinkRequest request){
        linkService.editLink(linkId, request);
        return ApiResult.ok(null);
    }

    @Operation(summary = "공유 링크 삭제")
    @DeleteMapping("/post/{groupId}/links/{linkId}")
    public ApiResult<Void> deleteLinks(
            @PathVariable Long groupId,
            @PathVariable Long linkId){
        linkService.deleteLink(linkId);
        return ApiResult.ok(null);
    }
}
