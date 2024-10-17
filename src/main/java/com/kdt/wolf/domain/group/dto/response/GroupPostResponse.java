package com.kdt.wolf.domain.group.dto.response;

import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class GroupPostResponse {
    private final Long groupPostId;
    private final GroupLeader leaderUser;
    private final String name;
    private final String shortIntro;
    private final String tag;
    private final String optionalRequirements;
    private final int targetMembers;
    private final String thumbnail;
    private final String topic;
    private final String description;
    private final String warning;
    private final char challengeStatus;
    private final String type;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LocalDate recruitStartDate;
    private final LocalDate recruitDeadlineDate;

    public GroupPostResponse(GroupPostEntity groupPost) {
        this.groupPostId = groupPost.getGroupPostId();
        this.leaderUser = new GroupLeader(groupPost.getLeaderUser());
        this.name = groupPost.getName();
        this.shortIntro = groupPost.getShortIntro();
        this.tag = groupPost.getTag();
        this.optionalRequirements = groupPost.getOptionalRequirements();
        this.targetMembers = groupPost.getTargetMembers();
        this.thumbnail = groupPost.getThumbnail();
        this.topic = groupPost.getTopic();
        this.description = groupPost.getDescription();
        this.warning = groupPost.getWarning();
        this.challengeStatus = groupPost.getChallengeStatus();
        this.type = groupPost.getType() != null ? groupPost.getType().toString().toLowerCase() : null; // GroupType을 String으로 변환
        this.startDate = groupPost.getStartDate() != null ? groupPost.getStartDate() : null;
        this.endDate = groupPost.getEndDate() != null ? groupPost.getEndDate() : null;
        this.recruitStartDate = groupPost.getRecruitStartDate() != null ? groupPost.getRecruitStartDate() : null;
        this.recruitDeadlineDate = groupPost.getRecruitDeadlineDate() != null ? groupPost.getRecruitDeadlineDate() : null;
    }

    @Getter
    public static class GroupLeader {
        private final Long userId;
        private final String userNickname;
        private final String userProfileImg;

        public GroupLeader(UserEntity user) {
            this.userId = user.getUserId();
            this.userNickname = user.getNickname();
            this.userProfileImg = user.getProfilePicture();
        }
    }
}
