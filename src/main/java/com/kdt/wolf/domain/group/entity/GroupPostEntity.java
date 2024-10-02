package com.kdt.wolf.domain.group.entity;

import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "group_post")
public class GroupPostEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_group_post_id")
    @SequenceGenerator(name = "seq_group_post_id", sequenceName = "group_post_sequence", allocationSize = 1)
    private Long groupPostId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity leaderUser;

    @Column(unique = true, nullable = false)
    private String name;

    private String shortIntro;
    private String tag;
    private String optionalRequirements;
    private int targetMembers;
    private String thumbnail;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 400)
    private String description;

    @Column(nullable = false, length = 1000)
    private String warning;

    @Column(length = 1)
    private char challengeStatus;

    @Enumerated(EnumType.STRING)
    private GroupType type;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate recruitStartDate;
    private LocalDate recruitDeadlineDate;

    @Builder
    public GroupPostEntity(String name, UserEntity leaderUser, GroupType type, LocalDate startDate, LocalDate endDate, LocalDate recruitStartDate,
                           LocalDate recruitDeadlineDate, String shortIntro, String tag, String optionalRequirements,
                           int targetMembers, String thumbnail, String title, String description, String warning, char challengeStatus) {
        this.name = name;
        this.leaderUser = leaderUser;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recruitStartDate = recruitStartDate;
        this.recruitDeadlineDate = recruitDeadlineDate;
        this.shortIntro = shortIntro;
        this.tag = tag;
        this.optionalRequirements = optionalRequirements;
        this.targetMembers = targetMembers;
        this.thumbnail = thumbnail;
        this.title = title;
        this.description = description;
        this.warning = warning;
        this.challengeStatus = challengeStatus;
    }
}
