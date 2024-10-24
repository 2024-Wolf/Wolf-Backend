package com.kdt.wolf.domain.group.entity;

import com.kdt.wolf.domain.group.dto.request.GroupPostRequest;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private String topic;

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
                           int targetMembers, String thumbnail, String topic, String description, String warning, char challengeStatus) {
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
        this.topic = topic;
        this.description = description;
        this.warning = warning;
        this.challengeStatus = challengeStatus;
    }

    public void updateGroupPost(GroupPostRequest request) {
        this.name = request.getName();
        this.type = "study".equals(request.getType()) ? GroupType.STUDY : GroupType.PROJECT;
        this.topic = request.getTopic();
        this.description = request.getDescription();
        this.startDate = request.getStartDate();
        this.endDate = request.getEndDate();
        this.recruitStartDate = request.getRecruitStartDate();
        this.recruitDeadlineDate = request.getRecruitDeadlineDate();
        this.targetMembers = request.getTargetMembers();
        this.optionalRequirements = request.getOptionalRequirements();
        this.thumbnail = request.getThumbnail();
        this.tag = request.getTag();
        this.warning = request.getWarning();
    }

    public void updateThumbnail(String responseUrl) {
        this.thumbnail = responseUrl;
    }

    public void updateLeader(UserEntity user) {
        this.leaderUser = user;
    }
}
