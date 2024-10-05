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

    public ChallengeListResponse(ChallengePostEntity challengeEntity) {
        this.challengePostId = challengeEntity.getChallengePostId();
        this.img = challengeEntity.getImg();
        this.title = challengeEntity.getTitle();
        this.date = challengeEntity.getDate().toString();
        this.deadline = challengeEntity.getDeadline().toString();
    }

}
