package com.kdt.wolf.domain.report.controller;

import com.kdt.wolf.domain.report.dto.ReportAdminDto.ReportDetailDto;
import com.kdt.wolf.domain.report.dto.ReportAdminDto.ReportPreviewDto;
import com.kdt.wolf.domain.report.service.ReportAction;
import com.kdt.wolf.domain.report.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin/reports")
public class ReportAdminController {
    private final ReportService reportService;

    @Operation(summary = "신고 전체 조회")
    @GetMapping("")
    public String findAllReports(Model model) {
        List<ReportPreviewDto> response = reportService.findAllReports();
        model.addAttribute("reports", response);
        return "report"; //report.jsp
    }

    @Operation(summary = "신고 단일 조회")
    @GetMapping("/{reportId}")
    public String findReport(@PathVariable Long reportId, Model model) {
        ReportDetailDto response = reportService.findReport(reportId);
        model.addAttribute("report", response);
        return "reportDetail"; //reportDetail.jsp
    }

    @Operation(summary = "신고 처리 / ACTION : NOTHING, WARNING, SUSPEND, BAN")
    @PostMapping("/{reportId}")
    public String processReportAndNotify(@PathVariable Long reportId,
                                         @RequestParam("action") String action,
                                         @RequestParam("processContent") String processContent,
                                         Model model) {
        Long response = reportService.processReport(reportId, ReportAction.valueOf(action), processContent);

        model.addAttribute("response", response);
        model.addAttribute("action", action);
        model.addAttribute("processContent", processContent);

        return "redirect:/admin/reports"; //redirect to report.jsp
    }

}
