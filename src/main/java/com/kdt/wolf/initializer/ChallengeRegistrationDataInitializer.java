package com.kdt.wolf.initializer;

import com.kdt.wolf.domain.challenge.entity.ChallengePostEntity;
import com.kdt.wolf.domain.challenge.entity.ChallengeRegistrationEntity;
import com.kdt.wolf.domain.challenge.repository.ChallengePostRepository;
import com.kdt.wolf.domain.challenge.repository.ChallengeRegistrationQueryRepository;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.repository.GroupPostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class ChallengeRegistrationDataInitializer implements CommandLineRunner {

    private final ChallengeRegistrationQueryRepository challengeRegistrationRepository;
    private final ChallengePostRepository challengePostRepository;
    private final GroupPostRepository groupPostRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if (challengeRegistrationRepository.count() > 0) {
            return;
        }
        //insertChallengeRegistrationData();
        System.out.println("ChallengeRegistration 더미 데이터가 성공적으로 삽입되었습니다.");
    }

    @Transactional
    public void insertChallengeRegistrationData() {
        List<ChallengePostEntity> challengePosts = challengePostRepository.findAll();
        List<GroupPostEntity> groupPosts = groupPostRepository.findAll();

        // ChallengePostEntity와 GroupPostEntity가 있는 상태에서 더미 데이터 생성
        List<ChallengeRegistrationEntity> registrations = Arrays.asList(
                new ChallengeRegistrationEntity(challengePosts.get(0), groupPosts.get(0), 1000L),
                new ChallengeRegistrationEntity(challengePosts.get(1), groupPosts.get(1), 2000L),
                new ChallengeRegistrationEntity(challengePosts.get(2), groupPosts.get(2), 3000L),
                new ChallengeRegistrationEntity(challengePosts.get(3), groupPosts.get(3), 4000L),
                new ChallengeRegistrationEntity(challengePosts.get(4), groupPosts.get(4), 5000L)
        );

        challengeRegistrationRepository.saveAll(registrations); // 등록된 챌린지 정보를 DB에 저장
    }
}
