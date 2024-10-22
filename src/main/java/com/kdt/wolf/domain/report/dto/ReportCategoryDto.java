package com.kdt.wolf.domain.report.dto;

public class ReportCategoryDto {
    public record ReportCategory(
            Long id,
            String category
    ) { }

}
