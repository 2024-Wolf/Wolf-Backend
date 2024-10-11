package com.kdt.wolf.domain.group.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EvaluateRequest {

    @NotNull
    private Long memberId;

    @NotNull
    private String rate;

    @Builder
    public EvaluateRequest(Long memberId, String rate) {
        this.memberId = memberId;
        this.rate = rate;
    }
}
