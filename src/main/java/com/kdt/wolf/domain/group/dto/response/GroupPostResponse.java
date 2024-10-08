package com.kdt.wolf.domain.group.dto.response;

import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;
import lombok.Getter;

@Getter
public class GroupPostResponse {
    private final Long groupPostId;
    private final UserEntity leaderUser;
    private final String leaderNickname; // 리더의 닉네임
    private final String name;
    private final String shortIntro;
    private final String tag;
    private final String optionalRequirements;
    private final int targetMembers;
    private final String thumbnail;
    private final String title;
    private final String description;
    private final String warning;
    private final char challengeStatus;
    private final String type; // GroupType을 String으로 변환
    private final String startDate;
    private final String endDate;
    private final String recruitStartDate;
    private final String recruitDeadlineDate;

    public GroupPostResponse(GroupPostEntity groupPost) {
        this.groupPostId = groupPost.getGroupPostId();
        this.leaderUser = groupPost.getLeaderUser();
        this.leaderNickname = leaderUser != null ? leaderUser.getNickname() : null; // 리더의 닉네임
        this.name = groupPost.getName();
        this.shortIntro = groupPost.getShortIntro();
        this.tag = groupPost.getTag();
        this.optionalRequirements = groupPost.getOptionalRequirements();
        this.targetMembers = groupPost.getTargetMembers();
        this.thumbnail = groupPost.getThumbnail();
        this.title = groupPost.getTitle();
        this.description = groupPost.getDescription();
        this.warning = groupPost.getWarning();
        this.challengeStatus = groupPost.getChallengeStatus();
        this.type = groupPost.getType() != null ? groupPost.getType().name() : null; // GroupType을 String으로 변환
        this.startDate = groupPost.getStartDate() != null ? groupPost.getStartDate().toString() : null;
        this.endDate = groupPost.getEndDate() != null ? groupPost.getEndDate().toString() : null;
        this.recruitStartDate = groupPost.getRecruitStartDate() != null ? groupPost.getRecruitStartDate().toString() : null;
        this.recruitDeadlineDate = groupPost.getRecruitDeadlineDate() != null ? groupPost.getRecruitDeadlineDate().toString() : null;
    }
}
