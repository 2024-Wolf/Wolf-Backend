package com.kdt.wolf.domain.challenge.service;

import com.kdt.wolf.domain.challenge.dao.ChallengePostDao;
import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview;
import com.kdt.wolf.domain.challenge.dto.ChallengeStatus;
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

        challengesByStatus.put("신청 가능",
                challengePostDao.findOngoingChallenges(groupId, userId)
                        .stream()
                        .map(post -> {
                            ChallengeStatus status = null;
                            return new ChallengePreview(post, status);
                        })
                        .toList()
        );
        return challengesByStatus;
    }

}
