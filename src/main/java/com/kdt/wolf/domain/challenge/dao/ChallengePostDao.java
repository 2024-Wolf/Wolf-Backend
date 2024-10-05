package com.kdt.wolf.domain.challenge.dao;

import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview;
import com.kdt.wolf.domain.challenge.repository.ChallengeRegistrationQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ChallengePostDao {

    private final ChallengeRegistrationQueryRepository challengeRegistrationQueryRepository;

    public List<ChallengePreview> findCertifiableChallenges(Long groupId, Long userId) {
        return challengeRegistrationQueryRepository.findCertifiableChallenges(groupId, userId);
    }

    public List<ChallengePreview> findCertifiedChallenges(Long groupId, Long userId) {
        return challengeRegistrationQueryRepository.findCertifiedChallenges(groupId, userId);
    }

    public List<ChallengePreview> findCompletedChallenges(Long groupId, Long userId) {
        return challengeRegistrationQueryRepository.findCompletedChallenges(groupId, userId);
    }


    public List<ChallengePreview> findJoinableChallenges(Long groupId, Long userId) {
        return challengeRegistrationQueryRepository.findJoinableChallenges(groupId, userId);
    }

    public List<ChallengePreview> findAvailableChallenges(Long groupId) {
        return challengeRegistrationQueryRepository.findApplicableChallenges(groupId);
    }
}
