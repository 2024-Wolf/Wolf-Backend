package com.kdt.wolf.domain.report.dao;

import com.kdt.wolf.domain.report.entity.ReportCategoryEntity;
import com.kdt.wolf.domain.report.entity.ReportEntity;
import com.kdt.wolf.domain.report.repository.ReportCategoryRepository;
import com.kdt.wolf.domain.report.repository.ReportRepository;
import com.kdt.wolf.global.exception.NotFoundException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportDao {
    private final ReportRepository reportRepository;
    private final ReportCategoryRepository reportCategoryRepository;

    public ReportEntity save(ReportEntity reportEntity) {
        return reportRepository.save(reportEntity);
    }

    public ReportCategoryEntity findReportCategoryById(Long categoryId) {
        return reportCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_REPORT_CATEGORY));
    }

    public Page<ReportEntity> findAll(Pageable pageable) {

        return reportRepository.findAll(pageable);
    }

    public ReportEntity findById(Long reportId) {
        return reportRepository.findById(reportId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_REPORT));
    }
}
