package com.kdt.wolf.domain.report.controller;

import com.kdt.wolf.domain.report.dto.ReportCategoryDto.ReportCategory;
import com.kdt.wolf.domain.report.dto.ReportDto.CreateReportRequest;
import com.kdt.wolf.domain.report.service.ReportService;
import com.kdt.wolf.global.auth.dto.AuthenticatedUser;
import com.kdt.wolf.global.base.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {
    private final ReportService reportService;

    @Operation(summary = "신고하기 TYPE : GROUP, REPLY, QUESTION, USER")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResult<Long> createReport (@RequestBody CreateReportRequest request,
                                   @AuthenticationPrincipal AuthenticatedUser user) {
        Long response = reportService.createReport(request, user.getUserId());
        return ApiResult.ok(response);
    }

    @Operation(summary = "신고 카테고리 전체 조회")
    @GetMapping("/categories")
    public ApiResult<List<ReportCategory>> getReportCategories() {
        List<ReportCategory> response = reportService.getReportCategories();
        return ApiResult.ok(response);
    }
}
