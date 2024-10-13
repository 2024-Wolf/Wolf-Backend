package com.kdt.wolf.domain.report.dao;

import com.kdt.wolf.domain.report.entity.ReportProcessEntity;
import com.kdt.wolf.domain.report.repository.ReportProcessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportProcessDao {
    private final ReportProcessRepository reportProcessRepository;

    public void save(ReportProcessEntity reportProcessEntity) {
        reportProcessRepository.save(reportProcessEntity);
    }


}
