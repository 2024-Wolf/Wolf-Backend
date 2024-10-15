package com.kdt.wolf.domain.group.entity.common;

import lombok.Getter;

@Getter
public enum GroupStatus {
    APPLYING, // 신청한 모임
    ONGOING,  // 진행 중인 모임
    COMPLETED // 완료된 모임
}
