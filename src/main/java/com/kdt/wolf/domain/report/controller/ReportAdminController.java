package com.kdt.wolf.domain.report.controller;

import com.kdt.wolf.domain.report.dao.ReportAdminDto.ReportDetailDto;
import com.kdt.wolf.domain.report.dao.ReportAdminDto.ReportPreviewDto;
import com.kdt.wolf.domain.report.service.ReportService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/reports")
public class ReportAdminController {
    private final ReportService reportService;
    //- [ ]  **신고 단일 조회** : `GET /reports/{reportId}`
    //- [ ]  **신고 상태 변경** : `PATCH /reports/{reportId}`

    @GetMapping("")
    public String findAllReports() {
        List<ReportPreviewDto> response = reportService.findAllReports();
        return "";
    }

    @GetMapping("/{reportId}")
    public String findReport(@PathVariable Long reportId) {
        ReportDetailDto response = reportService.findReport(reportId);
        return "";
    }


}
