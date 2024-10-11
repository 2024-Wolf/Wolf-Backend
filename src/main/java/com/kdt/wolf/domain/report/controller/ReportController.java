package com.kdt.wolf.domain.report.controller;

import com.kdt.wolf.domain.report.dto.ReportDto.CreateReportRequest;
import com.kdt.wolf.domain.report.service.ReportService;
import com.kdt.wolf.global.auth.dto.AuthenticatedUser;
import com.kdt.wolf.global.base.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResult<Long> createReport (@RequestBody CreateReportRequest request,
                                   @AuthenticationPrincipal AuthenticatedUser user) {
        Long response = reportService.createReport(request, user.getUserId());
        return ApiResult.ok(response);
    }


}
