package com.kdt.wolf.domain.challenge.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private Date paymentDate;

    @Builder
    public void PaymentEntity(Long registrationId, Long userId) {
        this.registrationId = registrationId;
        this.userId = userId;
        this.paymentStatus = 'N';
        this.paymentDate = new Date();
    }

    public void updatePaymentStatus() {
        this.paymentStatus = 'Y';
    }

}