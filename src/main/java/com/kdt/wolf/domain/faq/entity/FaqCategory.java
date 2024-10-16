package com.kdt.wolf.domain.faq.entity;

import lombok.Getter;

@Getter
public enum FaqCategory {
    STUDY("스터디"),
    PROJECT("프로젝트"),
    CHALLENGE("챌린지"),
    ACCOUNT("계정"),
    ETC("기타");


    private final String name;

    FaqCategory(String name) {
        this.name = name;
    }
}
