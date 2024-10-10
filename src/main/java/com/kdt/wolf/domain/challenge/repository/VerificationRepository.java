package com.kdt.wolf.domain.challenge.repository;

import com.kdt.wolf.domain.challenge.entity.VerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<VerificationEntity, Long> {
}
