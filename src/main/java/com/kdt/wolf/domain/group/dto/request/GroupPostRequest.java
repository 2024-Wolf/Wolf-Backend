package com.kdt.wolf.domain.group.dto.request;

import com.kdt.wolf.domain.group.entity.common.GroupType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class GroupPostRequest {
    @NotNull
    private String name;

    @NotNull
    private Long leaderUser;

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
}
