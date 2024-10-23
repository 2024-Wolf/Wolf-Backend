package com.kdt.wolf.domain.notice.controller;

import com.kdt.wolf.domain.notice.dto.NoticeDto.NoticeDetailDto;
import com.kdt.wolf.domain.notice.dto.NoticeDto.NoticePreviewPageResponse;
import com.kdt.wolf.domain.notice.service.NoticeService;
import com.kdt.wolf.global.base.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/notices")
@RequiredArgsConstructor
@RestController
public class NoticeController {

    private final NoticeService noticeService;

    @Operation(summary = "공지사항 전체 목록 조회")
    @GetMapping
    public ApiResult<NoticePreviewPageResponse> getNotices(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        NoticePreviewPageResponse notices = noticeService.getNoticePreviews(pageable);
        return ApiResult.ok(notices);
    }

    @Operation(summary = "공지사항 단일 정보 조회")
    @GetMapping("/{noticeId}")
    public ApiResult<NoticeDetailDto> getNotice(@PathVariable Long noticeId, Model model) {
        NoticeDetailDto notice = noticeService.getNotice(noticeId);
        model.addAttribute("notice", notice);
        return ApiResult.ok(notice);
    }
}
