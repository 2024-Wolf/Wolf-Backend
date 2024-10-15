package com.kdt.wolf.domain.faq.repository;

import com.kdt.wolf.domain.faq.entity.FaqCategory;
import com.kdt.wolf.domain.faq.entity.FaqEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<FaqEntity, Long> {

    @Query("SELECT f FROM FaqEntity f WHERE f.category = :category")
    Page<FaqEntity> findByCategory(FaqCategory category, Pageable pageable);
}
