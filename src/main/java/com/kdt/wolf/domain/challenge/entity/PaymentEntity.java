package com.kdt.wolf.domain.challenge.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Entity
@RequiredArgsConstructor
@Table(name = "paymentEntity")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_payment_id")
    @SequenceGenerator(name = "seq_payment_id", sequenceName = "payment_sequence", allocationSize = 1)
    private Long paymentId;

    // 신청 id
    private Long registrationId;

    // 결제 회원 id
    private Long userId;

    private char paymentStatus;
    private LocalDate paymentDate;

    @Builder
    public void PaymentEntity(Long registrationId, Long userId) {
        this.registrationId = registrationId;
        this.userId = userId;
        this.paymentStatus = 'N';
        this.paymentDate = LocalDate.now();
    }

    public void updatePaymentStatus() {
        this.paymentStatus = 'Y';
    }

}