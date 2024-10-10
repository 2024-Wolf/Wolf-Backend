package com.kdt.wolf.domain.challenge.repository;

import com.kdt.wolf.domain.challenge.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChallengePaymentRepository extends JpaRepository<PaymentEntity, Long> {
}
