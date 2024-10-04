package com.kdt.wolf.domain.challenge.dto.response;

import com.kdt.wolf.domain.challenge.entity.ChallengePostEntity;
import lombok.Getter;

@Getter
public class ChallengeListResponse {

    private final Long challengePostId;
    private final String img;
    private final String title;
    private final String date;
    private final String deadline;
    private final String state;

    public ChallengeListResponse(ChallengePostEntity challengeEntity, String state) {
        this.challengePostId = challengeEntity.getChallengePostId();
        this.img = challengeEntity.getImg();
        this.title = challengeEntity.getTitle();
        this.date = challengeEntity.getDate().toString();
        this.deadline = challengeEntity.getDeadline().toString();
        this.state = state;
    }

}
