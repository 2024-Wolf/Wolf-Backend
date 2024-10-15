package com.kdt.wolf.domain.group.dto.request;

import com.kdt.wolf.domain.group.dto.Recruitments;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import com.kdt.wolf.domain.user.entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class GroupPostRequest {
    @NotNull
    private String name;

    @NotNull
    private String type;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate recruitStartDate;
    private LocalDate recruitDeadlineDate;
    private String shortIntro;
    private String tag;
    private String optionalRequirements;
    private List<Recruitments> recruitments;
    private int targetMembers;
    private String thumbnail;
    @NotNull
    private String topic;
    @NotNull
    private String description;
    @NotNull
    private String warning;
    private char challengeStatus;

    @Builder
    public GroupPostRequest(String name, String type, LocalDate startDate, LocalDate endDate, LocalDate recruitStartDate,
                            LocalDate recruitDeadlineDate, String shortIntro, String tag, String optionalRequirements, List<Recruitments> recruitments,
                            int targetMembers, String thumbnail, String topic, String description, String warning, char challengeStatus) {
        this.name = name;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recruitStartDate = recruitStartDate;
        this.recruitDeadlineDate = recruitDeadlineDate;
        this.shortIntro = shortIntro;
        this.tag = tag;
        this.optionalRequirements = optionalRequirements;
        this.recruitments = recruitments;
        this.targetMembers = targetMembers;
        this.thumbnail = thumbnail;
        this.topic = topic;
        this.description = description;
        this.warning = warning;
        this.challengeStatus = challengeStatus;
    }
}

