package com.kdt.wolf.domain.report.repository;

import com.kdt.wolf.domain.report.entity.ReportCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportCategoryRepository extends JpaRepository<ReportCategoryEntity, Long> {
}
