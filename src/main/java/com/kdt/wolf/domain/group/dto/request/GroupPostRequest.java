package com.kdt.wolf.domain.group.dto.request;

import com.kdt.wolf.domain.group.entity.common.GroupType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class GroupPostRequest {
    @NotNull
    private String name;

    @NotNull
    private Long leaderUser;

    @NotNull
    private GroupType type;

    private Date startDate;
    private Date endDate;
    private Date recruitStartDate;
    private Date recruitDeadlineDate;
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
}
