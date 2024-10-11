package com.kdt.wolf.domain.report.controller;

import com.kdt.wolf.domain.report.dao.ReportAdminDto.ReportPreviewDto;
import com.kdt.wolf.domain.report.service.ReportService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/reports")
public class ReportAdminController {
    private final ReportService reportService;

    @GetMapping("")
    public String findAllReports() {
        List<ReportPreviewDto> response = reportService.findAllReports();
        return "";
    }



}
