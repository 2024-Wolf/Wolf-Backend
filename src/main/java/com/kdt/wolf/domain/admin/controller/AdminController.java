package com.kdt.wolf.domain.admin.controller;

import com.kdt.wolf.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class AdminController {
    private final AdminService adminService;


}
