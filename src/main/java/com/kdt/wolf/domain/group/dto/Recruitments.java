package com.kdt.wolf.domain.group.dto;

import com.kdt.wolf.domain.group.entity.common.RecruitRole;
import lombok.Getter;

@Getter
public class Recruitments {
    private final RecruitRole recruitRole;
    private final int recruitRoleCnt;

    public Recruitments (RecruitRole recruitRole, int recruitRoleCnt) {
        this.recruitRole = recruitRole;
        this.recruitRoleCnt = recruitRoleCnt;
    }
}
