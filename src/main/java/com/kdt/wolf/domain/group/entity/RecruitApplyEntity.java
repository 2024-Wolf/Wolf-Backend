package com.kdt.wolf.domain.group.entity;

import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "recruit_apply")
public class RecruitApplyEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_recruit_apply_id")
    @SequenceGenerator(name = "seq_recruit_apply_id", sequenceName = "recruit_apply_sequence", allocationSize = 1)
    private Long recruitApplyId;

    @ManyToOne
    @JoinColumn(name = "group_post_id", nullable = false)
    private GroupPostEntity groupPost;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "recruit_role_id", nullable = false)
    private RecruitRoleEntity position;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String applicationReason;

    @Column(length = 1000)
    private String introduction;

    @Column(length = 200)
    private String techStack;

    @Column
    private String portfolioLink;

    @Column
    private String availableDays;

    @Column(length = 1000)
    private String additionalNotes;

    @Builder
    public RecruitApplyEntity(GroupPostEntity groupPost, UserEntity user, RecruitRoleEntity position, String email, String applicationReason,
                              String introduction, String techStack, String portfolioLink, String availableDays,
                              String additionalNotes) {
        this.groupPost = groupPost;
        this.user = user;
        this.position = position;
        this.email = email;
        this.applicationReason = applicationReason;
        this.introduction = introduction;
        this.techStack = techStack;
        this.portfolioLink = portfolioLink;
        this.availableDays = availableDays;
        this.additionalNotes = additionalNotes;
    }
}