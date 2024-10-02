package com.kdt.wolf.domain.challenge.dao;

import com.kdt.wolf.domain.challenge.repository.ChallengePostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChallengePostDao {

    private final ChallengePostRepository challengePostRepository;
}
