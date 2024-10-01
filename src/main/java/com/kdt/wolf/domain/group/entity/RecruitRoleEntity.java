package com.kdt.wolf.domain.group.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "recruit_role")
public class RecruitRoleEntity {

    @Id
    @Column(length = 50, nullable = false)
    private String recruitRoleId;

    @Column(length = 50)
    private String recruitRoleIcon;

    @Builder
    public RecruitRoleEntity(String recruitRoleId, String recruitRoleIcon) {
        this.recruitRoleId = recruitRoleId;
        this.recruitRoleIcon = recruitRoleIcon;
    }
}
