package com.kdt.wolf.domain.report.dto;

import com.kdt.wolf.domain.report.entity.ReportEntity;
import com.kdt.wolf.domain.report.entity.ReportTopic;

public class ReportDto {
    public record CreateReportRequest(
            ReportTopic reportTopic,
            Long targetId,
            Long reportCategoryId,
            String reportReason
    ) { }
}
