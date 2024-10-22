package com.kdt.wolf.domain.notice.controller;

import com.kdt.wolf.domain.notice.dto.NoticeDto.NoticeResponseDto;
import com.kdt.wolf.domain.notice.service.NoticeService;
import com.kdt.wolf.global.base.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/notices")
@RequiredArgsConstructor
@RestController
public class NoticeController {

    private final NoticeService noticeService;

    @Operation(summary = "공지사항 목록 조회")
    @GetMapping
    public ApiResult<List<NoticeResponseDto>> getNotices(@PageableDefault(page = 0, size = 20) Pageable pageable) {
        List<NoticeResponseDto> responses = noticeService.getNotices(pageable).notices();
        return ApiResult.ok(responses);
    }

}
