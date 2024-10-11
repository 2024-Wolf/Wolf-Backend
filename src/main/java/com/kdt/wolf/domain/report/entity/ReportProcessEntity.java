package com.kdt.wolf.domain.report.entity;

import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "report_process")
public class ReportProcessEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_report_process_id")
    @SequenceGenerator(name = "seq_report_process_id", sequenceName = "report_process_sequence", allocationSize = 1)
    private Long reportProcessId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_id", nullable = false)
    private ReportEntity report;

    @Column(nullable = false)
    private String reportProcessContent;

    @Builder
    public ReportProcessEntity(ReportEntity report, String reportProcessContent) {
        this.report = report;
        this.reportProcessContent = reportProcessContent;
    }

    public void updateProcessContent(String newContent) {
        this.reportProcessContent = newContent;
    }
}
