package com.kdt.wolf.domain.report.dao;

public class ReportAdminDto {
    public record ReportPreviewDto(
            //신고자, 신고 내용, 신고 대상, 신고 날짜, 처리 여부
            Long id,
            String reporter,
            String content,
            String target,
            String createdAt,
            boolean isProcessed
    ) { }

    public record ReportDetailDto(
            //신고자, 신고 내용, 신고 대상, 신고 날짜, 처리 여부, 처리 날짜
            Long id,
            String reporter,
            String content,
            String target,
            String targetContent,
            String createdAt,
            boolean isProcessed
    ) { }
    public record ProcessReportRequest(
            String processContent
    ) { }

}
