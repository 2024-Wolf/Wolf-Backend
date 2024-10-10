package com.kdt.wolf.domain.group.entity;
import com.kdt.wolf.domain.group.entity.common.GroupRecruitmentId;

import com.kdt.wolf.domain.group.entity.common.RecruitRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "recruitments")
public class RecruitmentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_recruitment_id")
    @SequenceGenerator(name = "seq_recruitment_id", sequenceName = "recruitment_sequence", allocationSize = 1)
    private Long recruitmentId;

    @ManyToOne
    @JoinColumn(name = "group_post_id", nullable = false)
    private GroupPostEntity groupPost;

    @Column
    private RecruitRole recruitRole; //ENUM

    @Column(nullable = false)
    private int recruitRoleCnt;

    @Builder
    public RecruitmentsEntity(GroupPostEntity groupPost, RecruitRole recruitRole, int recruitRoleCnt) {
        this.groupPost = groupPost;
        this.recruitRole = recruitRole;
        this.recruitRoleCnt = recruitRoleCnt;
    }
}
