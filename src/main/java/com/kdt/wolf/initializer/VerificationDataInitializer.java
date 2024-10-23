package com.kdt.wolf.initializer;

import com.kdt.wolf.domain.challenge.entity.ChallengePostEntity;
import com.kdt.wolf.domain.challenge.entity.ChallengeRegistrationEntity;
import com.kdt.wolf.domain.challenge.entity.VerificationEntity;
import com.kdt.wolf.domain.challenge.repository.ChallengePostRepository;
import com.kdt.wolf.domain.challenge.repository.ChallengeRegistrationQueryRepository;
import com.kdt.wolf.domain.challenge.repository.VerificationRepository;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")  // 개발 환경에서만 실행
@RequiredArgsConstructor
public class VerificationDataInitializer implements CommandLineRunner {

    private final VerificationRepository verificationRepository;
    private final ChallengePostRepository challengePostRepository;
    private final UserRepository userRepository;
    private final ChallengeRegistrationQueryRepository challengeRegistrationQueryRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if (verificationRepository.count() > 0) {
            return;
        }
        //insertVerificationData();
        System.out.println("Verification 더미 데이터가 성공적으로 삽입되었습니다.");
    }

    @Transactional
    public void insertVerificationData() {
        List<ChallengePostEntity> challengePosts = challengePostRepository.findAll(); // 모든 챌린지 가져오기
        List<UserEntity> users = userRepository.findAll(); // 모든 사용자 가져오기
        List<ChallengeRegistrationEntity> registration = challengeRegistrationQueryRepository.findAll();

        // 더미 데이터 생성
        List<VerificationEntity> verifications = Arrays.asList(
                VerificationEntity.builder()
                        .challengePost(challengePosts.get(0))
                        .user(users.get(0))
                        .registration(registration.get(0))
                        .certificationNo("CERT12345")
                        .institutionName("Certification Org")
                        .content("Verification for challenge 1")
                        .build(),
                VerificationEntity.builder()
                        .challengePost(challengePosts.get(1))
                        .user(users.get(1))
                        .registration(registration.get(1))
                        .certificationNo("CERT23456")
                        .institutionName("Certification Authority")
                        .content("Verification for challenge 2")
                        .build(),
                VerificationEntity.builder()
                        .challengePost(challengePosts.get(2))
                        .user(users.get(2))
                        .registration(registration.get(2))
                        .certificationNo("CERT34567")
                        .institutionName("Verifier Corp")
                        .content("Verification for challenge 3")
                        .build()
                // 필요에 따라 더 추가 가능
        );

        verificationRepository.saveAll(verifications); // 데이터베이스에 저장
    }
}
