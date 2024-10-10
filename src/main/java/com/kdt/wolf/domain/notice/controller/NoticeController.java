package com.kdt.wolf.domain.notice.controller;

import com.kdt.wolf.domain.notice.dto.NoticeDto.NoticeResponseDto;
import com.kdt.wolf.domain.notice.service.NoticeService;
import com.kdt.wolf.global.base.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/notices")
@RequiredArgsConstructor
@RestController
public class NoticeController {

    private final NoticeService noticeService;

    @Operation(summary = "공지사항 목록 조회")
    @GetMapping
    public ApiResult<List<NoticeResponseDto>> getNotices() {
        List<NoticeResponseDto> responses = noticeService.getNotices();
        return ApiResult.ok(responses);
    }

}
