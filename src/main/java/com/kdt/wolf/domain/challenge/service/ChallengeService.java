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



        challengesByStatus.put(ChallengeStatus.CERTIFICATION.getDescription(),
                challengePostDao.findCertifiableChallenges(groupId, userId)
                        .stream()
                        .map(post -> {
                            ChallengeStatus status = ChallengeStatus.CERTIFICATION;
                            return new ChallengePreview(post, status);
                        })
                        .toList()
        );

        challengesByStatus.put(ChallengeStatus.CERTIFICATION_COMPLETE.getDescription(),
                challengePostDao.findCertifiedChallenges(groupId, userId)
                        .stream()
                        .map(post -> {
                            ChallengeStatus status = ChallengeStatus.APPLY;
                            return new ChallengePreview(post, status);
                        })
                        .toList()
        );

        challengesByStatus.put(ChallengeStatus.RESULT_CONFIRM.getDescription(),
                challengePostDao.findCompletedChallenges(groupId, userId)
                        .stream()
                        .map(post -> {
                            ChallengeStatus status = ChallengeStatus.RESULT_CONFIRM;
                            return new ChallengePreview(post, status);
                        })
                        .toList()
        );

        challengesByStatus.put(ChallengeStatus.APPLY.getDescription(),
                challengePostDao.findAvailableChallenges(groupId)
                        .stream()
                        .map(post -> {
                            ChallengeStatus status = ChallengeStatus.APPLY;
                            return new ChallengePreview(post, status);
                        })
                        .toList()
        );

        challengesByStatus.put(ChallengeStatus.PARTICIPATE.getDescription(),
                challengePostDao.findJoinableChallenges(groupId, userId)
                        .stream()
                        .map(post -> {
                            ChallengeStatus status = ChallengeStatus.PARTICIPATE;
                            return new ChallengePreview(post, status);
                        })
                        .toList()
        );
        return challengesByStatus;
    }

}
