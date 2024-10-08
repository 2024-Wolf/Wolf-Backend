package com.kdt.wolf.domain.faq.repository;

import com.kdt.wolf.domain.faq.entity.FaqEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<FaqEntity, Long> {

}
