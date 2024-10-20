package com.kdt.wolf.domain.notice.controller;

import com.kdt.wolf.domain.admin.repository.AdminRepository;
import com.kdt.wolf.domain.faq.dto.FaqDto;
import com.kdt.wolf.domain.notice.dao.NoticeAdminDto.NoticeCreateDto;
import com.kdt.wolf.domain.notice.dao.NoticeAdminDto.NoticeDetailDto;
import com.kdt.wolf.domain.notice.dao.NoticeAdminDto.NoticePreviewDto;
import com.kdt.wolf.domain.notice.service.NoticeService;
import com.kdt.wolf.global.auth.dto.AuthenticatedUser;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/notices")
public class NoticeAdminController {
    private final NoticeService noticeService;
    private final AdminRepository adminRepository;

    /**
     * 공지사항 전체 목록 조회 : `GET /notices`
     * 공지사항 단일 정보 조회 : `GET /notices/{noticeId}`
     * 공지사항 등록 : `POST /notices`
     * 공지사항 수정 : `PUT /notices/{noticeId}`
     * 공지사항 삭제 : `DELETE /notices/{noticeId}`
     */

    @Operation(summary = "공지사항 전체 목록 조회")
    @GetMapping
    public String getNotices(Model model) {
        List<NoticePreviewDto> notices = noticeService.getNoticePreviews();
        model.addAttribute("notices", notices);
        return "notice";
    }

    @Operation(summary = "공지사항 단일 정보 조회")
    @GetMapping("/{noticeId}")
    public String getNotice(@PathVariable Long noticeId, Model model) {
        NoticeDetailDto notice = noticeService.getNotice(noticeId);
        model.addAttribute("notice", notice);
        return "noticeDetail";
    }

    @Operation(summary = "공지사항 수정 화면")
    @GetMapping("/noticeEdit/{noticeId}")
    public String editNoticeView(@PathVariable Long noticeId, Model model) {
        NoticeDetailDto notice = noticeService.getNotice(noticeId);
        model.addAttribute("notice", notice);
        return "noticeEdit";
    }

    @Operation(summary = "공지사항 등록")
    @PostMapping
    public String createNotice(@AuthenticationPrincipal AuthenticatedUser author,
                               @ModelAttribute NoticeCreateDto request) {
        //TODO : 로그인된 토큰 정보로 어드민 가져와야하는데...... 이부분 어캐 함 ? -> ..글쎄,,,
        Long noticeId = noticeService.createNotice(request, adminRepository.findAll().get(0).getAdminId());
        return "redirect:/admin/notices/" + noticeId;
    }

    @Operation(summary = "공지사항 수정")
    @PutMapping("/{noticeId}")
    public String updateNotice(@PathVariable Long noticeId,
                               @ModelAttribute NoticeCreateDto request, Model model) {
        Long updatedNoticeId = noticeService.updateNotice(noticeId, request);
        NoticeDetailDto notice = noticeService.getNotice(updatedNoticeId);
        model.addAttribute("notice", notice);
        return "redirect:/admin/notices/{noticeId}";
    }

    @Operation(summary = "공지사항 삭제")
    @DeleteMapping("/{noticeId}")
    public String deleteNotice(@PathVariable Long noticeId) {
        noticeService.deleteNotice(noticeId);
        return "redirect:/admin/notices";
    }
}
