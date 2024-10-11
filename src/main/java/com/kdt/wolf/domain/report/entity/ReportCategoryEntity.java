package com.kdt.wolf.domain.report.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "report_category")
public class ReportCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_report_category_id")
    @SequenceGenerator(name = "seq_report_category_id", sequenceName = "report_category_sequence", allocationSize = 1)
    private Long reportCategoryId;

    @Column(length = 200, nullable = false)
    private String reportCategoryContent;

    @Builder
    public ReportCategoryEntity(String reportCategoryContent) {
        this.reportCategoryContent = reportCategoryContent;
    }
}
