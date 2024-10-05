package com.kdt.wolf.domain.challenge.service;

import com.kdt.wolf.domain.challenge.dao.ChallengePostDao;
import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview;
import com.kdt.wolf.domain.challenge.dto.response.ChallengeListResponse;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChallengeService {

    private final ChallengePostDao challengePostDao;


    public Map<String, List<ChallengePreview>> getAllChallenges(Long groupId, Long userId){
        Map<String, List<ChallengePreview>> challengesByStatus = new HashMap<>();

        challengesByStatus.put("신청 가능", challengePostDao.findOngoingChallenges(groupId, userId));

//        challengesByStatus.put("참여 가능",
//                challengePostDao.payableChallenges(groupId, userId)
//                        .stream()
//                        .map(post -> new ChallengeListResponse(post))
//                        .toList());
//
//        challengesByStatus.put("인증 가능",
//                challengePostDao.certifiableChallenges(groupId, userId)
//                        .stream()
//                        .map(post -> new ChallengeListResponse(post))
//                        .toList());
//
//        challengesByStatus.put("인증 완료",
//                challengePostDao.certificateCompleteChallenges(groupId, userId)
//                        .stream()
//                        .map(post -> new ChallengeListResponse(post))
//                        .toList());
//
//        challengesByStatus.put("종료",
//                challengePostDao.finishedChallenges(groupId)
//                        .stream()
//                        .map(post -> new ChallengeListResponse(post))
//                        .toList());

        return challengesByStatus;
    }

}
