package com.kdt.wolf.domain.group.controller;

import com.kdt.wolf.domain.group.dto.GroupAdminDto.GroupDetailResponse;
import com.kdt.wolf.domain.group.dto.GroupAdminDto.GroupPreviewPageResponse;
import com.kdt.wolf.domain.group.service.GroupAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/groups")
public class GroupAdminController {
    private final GroupAdminService groupAdminService;


    @GetMapping("")
    public String getPosts(@PageableDefault(page = 0, size = 20) Pageable pageable,
                           Model model) {
        GroupPreviewPageResponse response = groupAdminService.getPosts(pageable);
        model.addAttribute("groups", response.groups());
        model.addAttribute("page", response.page());
        return "group"; // group.jsp를 반환
    }

    @GetMapping("/{groupId}")
    public String getGroupDetail(@PathVariable Long groupId, Model model) {
        GroupDetailResponse groupDetail = groupAdminService.getGroupDetail(groupId);

        model.addAttribute("groupDetail", groupDetail);

        return "groupDetail";
    }
}
