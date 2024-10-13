package com.kdt.wolf.domain.report.repository;

import com.kdt.wolf.domain.report.entity.ReportProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportProcessRepository extends JpaRepository<ReportProcessEntity, Long> {
}
