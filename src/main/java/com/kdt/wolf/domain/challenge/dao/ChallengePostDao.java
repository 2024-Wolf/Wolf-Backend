package com.kdt.wolf.domain.challenge.dao;

import com.kdt.wolf.domain.challenge.dto.ChallengeDto.ChallengePreview;
import com.kdt.wolf.domain.challenge.entity.ChallengePostEntity;
import com.kdt.wolf.domain.challenge.repository.ChallengePostRepository;
import com.kdt.wolf.domain.challenge.repository.ChallengeRegistrationQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ChallengePostDao {

    private final ChallengePostRepository challengePostRepository;
    private final ChallengeRegistrationQueryRepository challengeRegistrationRepository;

    public List<ChallengePreview> findOngoingChallenges(Long groupId, Long userId) {
        return challengeRegistrationRepository.findOngoingChallenges(groupId, userId);
    }

}
