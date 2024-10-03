package com.kdt.wolf.domain.challenge.dao;

import com.kdt.wolf.domain.challenge.entity.ChallengePostEntity;
import com.kdt.wolf.domain.challenge.repository.ChallengePostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ChallengePostDao {

    @Autowired
    private final ChallengePostRepository challengePostRepository;

    private List<ChallengePostEntity> appliableChallenges(Long groupId){
        return challengePostRepository.findAppliable(groupId);
    }

}
