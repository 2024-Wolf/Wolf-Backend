package com.kdt.wolf.domain.group.dto.request;

import com.kdt.wolf.domain.group.entity.common.GroupType;
import com.kdt.wolf.domain.user.entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class GroupPostRequest {
    @NotNull
    private String name;

    @NotNull
    private UserEntity leaderUser;

    @NotNull
    private GroupType type;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate recruitStartDate;
    private LocalDate recruitDeadlineDate;
    private String shortIntro;
    private String tag;
    private String optionalRequirements;
    private int targetMembers;
    private String thumbnail;
    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String warning;
    private char challengeStatus;

    @Builder
    public GroupPostRequest(String name, UserEntity leaderUser, GroupType type, LocalDate startDate, LocalDate endDate, LocalDate recruitStartDate,
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

