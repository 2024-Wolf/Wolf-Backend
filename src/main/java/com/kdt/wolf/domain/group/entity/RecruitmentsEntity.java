package com.kdt.wolf.domain.group.entity;
import com.kdt.wolf.domain.group.entity.common.GroupRecruitmentId;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@IdClass(GroupRecruitmentId.class)
@Table(name = "recruitments")
public class RecruitmentsEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "group_post_id", nullable = false)
    private GroupPostEntity groupPost;

    @Id
    @ManyToOne
    @JoinColumn(name = "recruitRoleId", nullable = false)
    private RecruitRoleEntity recruitRole;

    @Column(nullable = false)
    private int recruitRoleCnt;

    @Builder
    public RecruitmentsEntity(GroupPostEntity groupPost, RecruitRoleEntity recruitRole, int recruitRoleCnt) {
        this.groupPost = groupPost;
        this.recruitRole = recruitRole;
        this.recruitRoleCnt = recruitRoleCnt;
    }
}
