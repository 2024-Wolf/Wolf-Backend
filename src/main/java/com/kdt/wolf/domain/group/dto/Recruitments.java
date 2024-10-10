package com.kdt.wolf.domain.group.dto;

import com.kdt.wolf.domain.group.entity.common.RecruitRole;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Recruitments {
    private final RecruitRole recruitRole;
    private final int recruitRoleCnt;

    @Builder
    public Recruitments (RecruitRole recruitRole, int recruitRoleCnt) {
        this.recruitRole = recruitRole;
        this.recruitRoleCnt = recruitRoleCnt;
    }
}
