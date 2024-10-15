package com.kdt.wolf.domain.report.controller;

import com.kdt.wolf.domain.report.dto.ReportAdminDto.ProcessReportRequest;
import com.kdt.wolf.domain.report.dto.ReportAdminDto.ReportDetailDto;
import com.kdt.wolf.domain.report.dto.ReportAdminDto.ReportPreviewDto;
import com.kdt.wolf.domain.report.service.ReportAction;
import com.kdt.wolf.domain.report.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/reports")
public class ReportAdminController {
    private final ReportService reportService;

    @Operation(summary = "신고 전체 조회")
    @GetMapping("")
    public String findAllReports() {
        List<ReportPreviewDto> response = reportService.findAllReports();
        return "";
    }

    @Operation(summary = "신고 단일 조회")
    @GetMapping("/{reportId}")
    public String findReport(@PathVariable Long reportId) {
        ReportDetailDto response = reportService.findReport(reportId);
        return "";
    }

    @Operation(summary = "신고 처리 / ACTION : NOTHING, WARNING, SUSPEND, BAN")
    @PatchMapping("/{reportId}")
    public String processReportAndNotify(@PathVariable Long reportId,
                                         @RequestBody ProcessReportRequest request) {
        Long response = reportService.processReport(reportId, request.action(), request.processContent());
        return "";
    }

}