package com.kdt.wolf.domain.alert.entity;

import lombok.Getter;

@Getter
public enum AlertType {
    GROUP("그룹"),
    CHALLENGE("챌린지"),
    COMMENT("댓글"),
    REPORT("신고"),;


    private final String type;

    AlertType(String type) {
        this.type = type;
    }
}
