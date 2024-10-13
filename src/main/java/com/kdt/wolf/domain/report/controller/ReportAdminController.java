package com.kdt.wolf.domain.report.controller;

import com.kdt.wolf.domain.report.dao.ReportAdminDto.ProcessReportRequest;
import com.kdt.wolf.domain.report.dao.ReportAdminDto.ReportDetailDto;
import com.kdt.wolf.domain.report.dao.ReportAdminDto.ReportPreviewDto;
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

    @Operation(summary = "신고 처리 상태만 변경")
    @PatchMapping("/{reportId}")
    public String processReport(@PathVariable Long reportId,
                                @RequestBody ProcessReportRequest request) {
        reportService.processReport(reportId);
        return "";
    }

    @Operation(summary = "경고 / 테스트 X")
    @PatchMapping("/warning/{reportId}")
    public String warningReport(@PathVariable Long reportId) {
        Long response = reportService.processWarningReport(reportId);
        return "";
    }

    //TODO : 3일 정지, 영구 정지
    @Operation(summary = "3일 정지 / 테스트 X")
    @PatchMapping("/suspension/{reportId}")
    public String suspensionReport(@PathVariable Long reportId) {
        Long response = reportService.processSuspendReport(reportId);
        return "";
    }

}
