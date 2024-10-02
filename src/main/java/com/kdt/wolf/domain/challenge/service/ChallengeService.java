package com.kdt.wolf.domain.challenge.service;

import com.kdt.wolf.domain.challenge.dao.ChallengePostDao;
import com.kdt.wolf.domain.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChallengeService {

    private final ChallengePostDao challengePostDao;

    @Autowired
    public ChallengeService(ChallengePostDao challengePostDao) {
        this.challengePostDao = challengePostDao;
    }
}
