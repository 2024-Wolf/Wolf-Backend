package com.kdt.wolf.domain.challenge.dao;

import com.kdt.wolf.domain.challenge.entity.ChallengePostEntity;
import com.kdt.wolf.domain.challenge.entity.ChallengeRegistrationEntity;
import com.kdt.wolf.domain.challenge.repository.ChallengeRegistrationQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ChallengePostDao {

    private final ChallengeRegistrationQueryRepository challengeRegistrationQueryRepository;

    public List<ChallengeRegistrationEntity> findCertifiableChallenges(Long groupId, Long userId) {
        return challengeRegistrationQueryRepository.findCertifiableChallenges(groupId, userId);
    }

    public List<ChallengeRegistrationEntity> findCertifiedChallenges(Long groupId, Long userId) {
        return challengeRegistrationQueryRepository.findCertifiedChallenges(groupId, userId);
    }

    public List<ChallengeRegistrationEntity> findCompletedChallenges(Long groupId, Long userId) {
        return challengeRegistrationQueryRepository.findCompletedChallenges(groupId, userId);
    }


    public List<ChallengeRegistrationEntity> findJoinableChallenges(Long groupId, Long userId) {
        return challengeRegistrationQueryRepository.findJoinableChallenges(groupId, userId);
    }

    public List<ChallengePostEntity> findAvailableChallenges(Long groupId) {
        return challengeRegistrationQueryRepository.findApplicableChallenges(groupId);
    }
}
