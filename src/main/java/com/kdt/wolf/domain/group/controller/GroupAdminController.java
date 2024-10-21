package com.kdt.wolf.domain.group.controller;

import com.kdt.wolf.domain.group.dto.GroupAdminDto.GroupPreviewPageResponse;
import com.kdt.wolf.domain.group.service.GroupAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/groups")
public class GroupAdminController {
    private final GroupAdminService groupAdminService;

    @GetMapping("")
    public String getPosts(Model model) {
        Pageable pageable = Pageable.ofSize(10);
        GroupPreviewPageResponse response = groupAdminService.getPosts(pageable);
        model.addAttribute("groups", response.groups());
        model.addAttribute("currentPage", response.page());           // 현재 페이지
        model.addAttribute("totalPages", response.totalPage());  // 전체 페이지 수
        return "group"; // group.jsp를 반환
    }


}
