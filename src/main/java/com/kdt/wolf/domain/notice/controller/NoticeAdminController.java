package com.kdt.wolf.domain.notice.controller;

import com.kdt.wolf.domain.notice.dao.NoticeAdminDto.NoticeDetailDto;
import com.kdt.wolf.domain.notice.dao.NoticeAdminDto.NoticePreviewDto;
import com.kdt.wolf.domain.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/notices")
public class NoticeAdminController {
    private final NoticeService noticeService;

    /**
     * 공지사항 전체 목록 조회 : `GET /notices`
     * 공지사항 단일 정보 조회 : `GET /notices/{noticeId}`
     * 공지사항 등록 : `POST /notices`
     * 공지사항 수정 : `PUT /notices/{noticeId}`
     * 공지사항 삭제 : `DELETE /notices/{noticeId}`
     */

    @Operation(summary = "공지사항 전체 목록 조회")
    @GetMapping("")
    public String getNotices() {
        List<NoticePreviewDto> notices = noticeService.getNoticePreviews();
        return "notice";
    }

    @Operation(summary = "공지사항 단일 정보 조회")
    @GetMapping("/{noticeId}")
    public String getNotice(@PathVariable Long noticeId) {
        NoticeDetailDto notice = noticeService.getNotice(noticeId);
        return "notice";
    }

    @Operation(summary = "공지사항 등록")
    @PostMapping("")
    public String createNotice() {

        return "notice";
    }

    @Operation(summary = "공지사항 수정")
    @PutMapping("/{noticeId}")
    public String updateNotice(@PathVariable Long noticeId) {
        return "notice";
    }

    @Operation(summary = "공지사항 삭제")
    @DeleteMapping("/{noticeId}")
    public String deleteNotice(@PathVariable Long noticeId) {
        return "notice";
    }
}
