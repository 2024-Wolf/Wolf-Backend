package com.kdt.wolf.domain.challenge.service;

import com.kdt.wolf.domain.challenge.dao.ChallengePostDao;
import com.kdt.wolf.domain.challenge.dto.response.ChallengeListResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChallengeService {

    private final ChallengePostDao challengePostDao;

    public ChallengeService(ChallengePostDao challengePostDao) {
        this.challengePostDao = challengePostDao;
    }

    public List<ChallengeListResponse> getAllChallenges(Long groupId, Long userId){
        List<ChallengeListResponse> listResponse = new ArrayList<>();

        challengePostDao.appliableChallenges(groupId).forEach(post ->
            listResponse.add(new ChallengeListResponse(post, "신청 가능"))
        );

        challengePostDao.payableChallenges(groupId, userId).forEach(post ->
            listResponse.add(new ChallengeListResponse(post, "참여 가능"))
        );

        challengePostDao.certifiableChallenges(groupId, userId).forEach(post ->
            listResponse.add(new ChallengeListResponse(post, "인증 가능"))
        );

        challengePostDao.certificateCompleteChallenges(groupId, userId).forEach(post ->
            listResponse.add(new ChallengeListResponse(post, "인증 완료"))
        );

        challengePostDao.finishedChallenges(groupId).forEach(post ->
            listResponse.add(new ChallengeListResponse(post, "종료"))
        );


        return listResponse;
    }

}
