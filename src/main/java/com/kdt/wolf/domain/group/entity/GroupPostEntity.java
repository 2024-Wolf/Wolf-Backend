package com.kdt.wolf.domain.group.entity;

import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "group_post")
public class GroupPostEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_group_post_group_post_id")
    @SequenceGenerator(name = "seq_group_post_group_post_id", sequenceName = "group_post_sequence", allocationSize = 1)
    private Long groupPostId;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", nullable = false)
    private UserEntity leaderUserId;

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

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date recruitStartDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date recruitDeadlineDate;

    @Builder
    public GroupPostEntity(String name, UserEntity leaderUserId, GroupType type, Date startDate, Date endDate, Date recruitStartDate,
                           Date recruitDeadlineDate, String shortIntro, String tag, String optionalRequirements,
                           int targetMembers, String thumbnail, String title, String description, String warning, char challengeStatus) {
        this.name = name;
        this.leaderUserId = leaderUserId;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recruitStartDate = recruitStartDate != null ? recruitStartDate : new Date();
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
