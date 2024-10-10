package com.kdt.wolf.domain.challenge.dto.response;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PaymentResponse {
    private final Long paymentId;
    private final String user;
    private final String group;
    private final String challenge;
    private final Long amount;
    private final String date;
    // 결제 방법(보류)
    // private String manner;

    public PaymentResponse(Long paymentId, String user, String group, String challenge, Long amount, LocalDate date) {
        this.paymentId = paymentId;
        this.user = user;
        this.group = group;
        this.challenge = challenge;
        this.amount = amount;
        this.date = date.toString();
    }
}
