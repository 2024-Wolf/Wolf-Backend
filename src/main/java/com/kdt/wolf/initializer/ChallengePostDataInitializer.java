package com.kdt.wolf.initializer;


import com.kdt.wolf.domain.challenge.entity.ChallengePostEntity;
import com.kdt.wolf.domain.challenge.repository.ChallengePostRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")  // 개발 환경에서만 실행
@RequiredArgsConstructor
public class ChallengePostDataInitializer  implements CommandLineRunner {
    private final ChallengePostRepository challengePostRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        if (challengePostRepository.count() > 0) {
            return;
        }
        insertChallengePostData(1L);
        System.out.println("ChallengePost 더미 데이터가 성공적으로 삽입되었습니다.");
    }

    @Transactional
    public void insertChallengePostData(Long adminUserId) {
        List<ChallengePostEntity> challengePosts = Arrays.asList(
                new ChallengePostEntity(adminUserId, "img1.png", "정보처리기사 자격증 대비", "정보처리기사 자격증을 목표로 공부하는 챌린지입니다.", "꾸준히 학습하기", "합격자에게 자격증 시험 응시료 환급!", LocalDate.now().plusDays(30)),
                new ChallengePostEntity(adminUserId, "img2.png", "컴퓨터 활용능력 1급 챌린지", "컴퓨터 활용능력 1급 시험을 준비하는 챌린지입니다.", "실습 위주로 학습하기", "합격자에게 엑셀/워드 강의 무료 제공", LocalDate.now().plusDays(25)),
                new ChallengePostEntity(adminUserId, "img3.png", "네트워크 관리사 2급 도전", "네트워크 관리사 2급을 준비하는 도전입니다.", "기출 문제 반복 학습", "합격자에게 네트워크 관련 서적 증정", LocalDate.now().plusDays(20)),
                new ChallengePostEntity(adminUserId, "img4.png", "SQLD 자격증 대비", "SQLD(데이터베이스 개발자) 자격증을 준비하는 챌린지입니다.", "SQL 문제 풀이 연습", "최고 성적자에게 SQL 전문가 과정 무료 수강권 제공", LocalDate.now().plusDays(15)),
                new ChallengePostEntity(adminUserId, "img5.png", "정보보안기사 자격증 대비", "정보보안기사를 목표로 공부하는 챌린지입니다.", "정보보안 관련 법률 학습", "합격자에게 정보보안 인증서 제공", LocalDate.now().plusDays(30)),
                new ChallengePostEntity(adminUserId, "img6.png", "리눅스 마스터 2급 도전", "리눅스 마스터 2급 자격증을 준비하는 도전입니다.", "리눅스 명령어 집중 학습", "합격자에게 리눅스 마스터 1급 응시료 지원", LocalDate.now().plusDays(20)),
                new ChallengePostEntity(adminUserId, "img7.png", "빅데이터 분석기사 준비", "빅데이터 분석기사 자격증을 준비하는 챌린지입니다.", "빅데이터 분석 및 활용 연습", "합격자에게 데이터 분석 강의 무료 제공", LocalDate.now().plusDays(25)),
                new ChallengePostEntity(adminUserId, "img8.png", "정보처리산업기사 대비", "정보처리산업기사 자격증을 준비하는 도전입니다.", "데이터베이스 설계 및 관리 연습", "합격자에게 산업기사 교재 제공", LocalDate.now().plusDays(18)),
                new ChallengePostEntity(adminUserId, "img9.png", "전자계산기기사 자격증 대비", "전자계산기기사 자격증을 목표로 공부하는 챌린지입니다.", "하드웨어 및 소프트웨어 기초 학습", "합격자에게 전자계산기 교재 증정", LocalDate.now().plusDays(22)),
                new ChallengePostEntity(adminUserId, "img10.png", "CCNA 자격증 대비", "Cisco CCNA 자격증을 목표로 한 도전입니다.", "네트워크 설정 및 관리 연습", "합격자에게 네트워크 장비 할인권 제공", LocalDate.now().plusDays(20)),
                new ChallengePostEntity(adminUserId, "img11.png", "AWS 자격증 준비", "AWS Certified Solutions Architect 자격증을 준비하는 챌린지입니다.", "클라우드 컴퓨팅 기초 학습", "합격자에게 AWS 크레딧 제공", LocalDate.now().plusDays(15)),
                new ChallengePostEntity(adminUserId, "img12.png", "정보보호산업기사 도전", "정보보호산업기사 자격증을 준비하는 도전입니다.", "정보보호 시스템 학습", "합격자에게 보안 관련 기기 제공", LocalDate.now().plusDays(28)),
                new ChallengePostEntity(adminUserId, "img13.png", "Python 프로그래밍 자격증 대비", "Python 프로그래밍 자격증을 목표로 학습하는 챌린지입니다.", "Python 기초 및 활용 연습", "합격자에게 프로그래밍 서적 제공", LocalDate.now().plusDays(10)),
                new ChallengePostEntity(adminUserId, "img14.png", "데이터베이스 관리사 자격증 준비", "데이터베이스 관리사 자격증을 준비하는 챌린지입니다.", "데이터베이스 설계 및 관리 연습", "합격자에게 관리사 응시료 지원", LocalDate.now().plusDays(35)),
                new ChallengePostEntity(adminUserId, "img15.png", "프로그래밍 언어 컴퓨팅 자격증 준비", "프로그래밍 언어와 컴퓨팅 자격증 대비 챌린지입니다.", "프로그래밍 언어 학습", "합격자에게 컴퓨팅 자격증 시험 응시료 환급", LocalDate.now().plusDays(27)),
                new ChallengePostEntity(adminUserId, "img16.png", "클라우드 보안 전문가 대비", "클라우드 보안 전문가 자격증을 목표로 하는 챌린지입니다.", "클라우드 보안 체계 학습", "합격자에게 보안 관련 서적 제공", LocalDate.now().plusDays(14)),
                new ChallengePostEntity(adminUserId, "img17.png", "프론트엔드 개발 자격증 대비", "프론트엔드 개발 자격증을 준비하는 챌린지입니다.", "HTML, CSS, JavaScript 학습", "합격자에게 프론트엔드 개발 툴 제공", LocalDate.now().plusDays(25)),
                new ChallengePostEntity(adminUserId, "img18.png", "백엔드 개발 자격증 대비", "백엔드 개발 자격증을 준비하는 도전입니다.", "Java, Spring 학습", "합격자에게 백엔드 개발 기기 제공", LocalDate.now().plusDays(29)),
                new ChallengePostEntity(adminUserId, "img19.png", "모바일 앱 개발 자격증 대비", "모바일 앱 개발 자격증을 준비하는 챌린지입니다.", "앱 개발 기본 및 앱 스토어 배포 학습", "합격자에게 모바일 앱 서적 제공", LocalDate.now().plusDays(17)),
                new ChallengePostEntity(adminUserId, "img20.png", "DevOps 자격증 대비", "DevOps 관련 자격증을 준비하는 챌린지입니다.", "CI/CD, 자동화 툴 학습", "합격자에게 DevOps 툴 이용권 제공", LocalDate.now().plusDays(21))
        );

        challengePostRepository.saveAll(challengePosts); // challengePosts 리스트를 데이터베이스에 저장
    }
}
