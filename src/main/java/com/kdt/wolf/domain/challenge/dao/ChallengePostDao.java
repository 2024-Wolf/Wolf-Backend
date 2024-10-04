package com.kdt.wolf.domain.challenge.dao;

import com.kdt.wolf.domain.challenge.entity.ChallengePostEntity;
import com.kdt.wolf.domain.challenge.repository.ChallengePostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChallengePostDao {

    private final ChallengePostRepository challengePostRepository;

    public ChallengePostDao(ChallengePostRepository challengePostRepository) {
        this.challengePostRepository = challengePostRepository;
    }

    // 신청 가능
    public List<ChallengePostEntity> appliableChallenges(Long groupId){
        return challengePostRepository.findAppliable(groupId);
    }

    // 참여 가능
    public List<ChallengePostEntity> payableChallenges(Long groupId, Long userId){
        return challengePostRepository.findPayable(groupId, userId);
    }

    // 인증 가능
    public List<ChallengePostEntity> certifiableChallenges(Long groupId, Long userId){
        return challengePostRepository.findCertifiable(groupId, userId);
    }

    // 인증 완료
    public List<ChallengePostEntity> certificateCompleteChallenges(Long groupId, Long userId){
        return challengePostRepository.findCertificationCompleted(groupId, userId);
    }

    // 결과 확인
    public List<ChallengePostEntity> finishedChallenges(Long groupId){
        return challengePostRepository.findChallengeFinished(groupId);
    }
}
