package com.kdt.wolf.initializer;

import com.kdt.wolf.domain.group.entity.GroupMemberEntity;
import com.kdt.wolf.domain.group.entity.GroupNewsEntity;
import com.kdt.wolf.domain.group.entity.GroupPostEntity;
import com.kdt.wolf.domain.group.entity.QuestionBoardEntity;
import com.kdt.wolf.domain.group.entity.QuestionCommentEntity;
import com.kdt.wolf.domain.group.entity.RecruitApplyEntity;
import com.kdt.wolf.domain.group.entity.RecruitmentsEntity;
import com.kdt.wolf.domain.group.entity.common.BoardType;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import com.kdt.wolf.domain.group.entity.common.MemberRole;
import com.kdt.wolf.domain.group.entity.common.RecruitRole;
import com.kdt.wolf.domain.group.repository.GroupMemberRepository;
import com.kdt.wolf.domain.group.repository.GroupNewsRepository;
import com.kdt.wolf.domain.group.repository.GroupPostRepository;
import com.kdt.wolf.domain.group.repository.QuestionBoardRepository;
import com.kdt.wolf.domain.group.repository.QuestionCommentRepository;
import com.kdt.wolf.domain.group.repository.RecruitApplyRepository;
import com.kdt.wolf.domain.group.repository.RecruitmentsRepository;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")  // 개발 환경에서만 실행
@RequiredArgsConstructor
public class GroupDataInitializer implements CommandLineRunner {
    private final GroupPostRepository groupPostRepository;
    private final GroupNewsRepository groupNewsRepository;
    private final RecruitmentsRepository recruitmentsRepository;
    private final GroupMemberRepository groupMemberRepository;
    private final RecruitApplyRepository recruitApplyRepository;
    private final QuestionBoardRepository questionBoardRepository;
    private final QuestionCommentRepository questionCommentRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        if(groupPostRepository.count() > 1) {
            System.out.println("Group 더미 데이터가 이미 존재합니다.");
            return;
        }

        // 유저 데이터 불러오기
        List<UserEntity> users = userRepository.findAll();

        if (users.size() < 5) {
            System.out.println("충분한 유저 데이터가 필요합니다.");
            return;
        }
        List<UserEntity> subset = users.subList(8, users.size());

        List<GroupPostEntity> groupPosts = insertGroupPostData(users);  // 그룹 포스트 데이터 삽입
        insertProjectRecruitmentData(groupPosts);  // 프로젝트 모집 데이터 삽입
        insertGroupLeaders(groupPosts);//리더 데이터 삽입
        List<RecruitApplyEntity> recruitApplies = insertRecruitApplyData(groupPosts, subset);  // 지원서 데이터 삽입
        insertGroupMembers(recruitApplies);  // 그룹 멤버 데이터 삽입
        insertGroupNewsData(groupPosts);  // 그룹 소식 데이터 삽입
        insertQuestionBoardData(groupPosts, users);  // 질문 데이터 삽입
        System.out.println("Group 더미 데이터가 성공적으로 삽입되었습니다.");
    }

    @Transactional
    public List<GroupPostEntity> insertGroupPostData(List<UserEntity> users) {
        List<GroupPostEntity> groupPosts = Arrays.asList(
                // STUDY Type Group Posts
                new GroupPostEntity(
                        "Spring Boot 스터디", users.get(0), GroupType.STUDY, LocalDate.now().plusDays(5), LocalDate.now().plusMonths(2),
                        LocalDate.now(), LocalDate.now().plusDays(10), "Spring Boot의 기본 개념과 실습",
                        "개발", "이메일,지원직군,지원사유,다룰 수 있는 언어", 10, "spring-boot-thumbnail.png", "Backend",
                        "Spring Boot에 대한 심화 학습을 진행합니다.", "학습 태도에 문제가 있을 시 제외됩니다.", 'Y'),

                new GroupPostEntity(
                        "알고리즘 스터디", users.get(1), GroupType.STUDY, LocalDate.now().plusDays(3), LocalDate.now().plusMonths(1),
                        LocalDate.now().minusDays(2), LocalDate.now().plusDays(5), "코딩 문제를 통한 알고리즘 학습",
                        "알고리즘", "이메일,지원직군,지원사유,참여가능 요일", 8, "algo-thumbnail.png", "Problem Solving",
                        "주 2회 알고리즘 문제풀이를 진행합니다.", "문제풀이에 소홀할 시 제외됩니다.", 'N'),

                new GroupPostEntity(
                        "데이터베이스 스터디", users.get(2), GroupType.STUDY, LocalDate.now().plusDays(7), LocalDate.now().plusMonths(3),
                        LocalDate.now().minusDays(1), LocalDate.now().plusDays(9), "SQL, NoSQL 데이터베이스 학습",
                        "데이터베이스", "이메일,지원직군,지원사유,자기소개", 12, "db-thumbnail.png", "Data Science",
                        "데이터베이스 모델링 및 실습을 진행합니다.", "수업을 무단으로 빠질 시 제외됩니다.", 'Y'),

                new GroupPostEntity(
                        "리액트 스터디", users.get(3), GroupType.STUDY, LocalDate.now().plusDays(2), LocalDate.now().plusMonths(1),
                        LocalDate.now(), LocalDate.now().plusDays(6), "리액트를 통한 웹 프론트엔드 개발 학습",
                        "프론트엔드", "이메일,지원직군,지원사유,포트폴리오 링크", 15, "react-thumbnail.png", "Frontend",
                        "React 기본과 실습을 병행합니다.", "소통이 부족할 시 제외됩니다.", 'N'),

                // PROJECT Type Group Posts
                new GroupPostEntity(
                        "AI 기반 추천 시스템 프로젝트", users.get(4), GroupType.PROJECT, LocalDate.now().plusDays(10), LocalDate.now().plusMonths(4),
                        LocalDate.now(), LocalDate.now().plusDays(15), "AI 모델을 활용한 추천 시스템 구축",
                        "AI", "이메일,지원직군,지원사유,자유기재", 5, "ai-project-thumbnail.png", "Artificial Intelligence",
                        "추천 시스템을 설계하고 구현하는 프로젝트입니다.", "프로젝트에 소홀할 시 제외됩니다.", 'N'),

                new GroupPostEntity(
                        "Java 웹 개발 프로젝트", users.get(5), GroupType.PROJECT, LocalDate.now().plusDays(8), LocalDate.now().plusMonths(3),
                        LocalDate.now(), LocalDate.now().plusDays(12), "Java 기반의 웹 애플리케이션 개발",
                        "Java", "이메일,지원직군,지원사유,다룰 수 있는 언어,자기소개,자유기재", 6, "java-web-thumbnail.png", "Backend",
                        "Java와 Spring을 사용한 웹 애플리케이션 개발 프로젝트입니다.", "지속적인 참여가 필요합니다.", 'N'),

                new GroupPostEntity(
                        "모바일 앱 개발 프로젝트", users.get(6), GroupType.PROJECT, LocalDate.now().plusDays(9), LocalDate.now().plusMonths(4),
                        LocalDate.now(), LocalDate.now().plusDays(14), "안드로이드 앱 개발 프로젝트",
                        "모바일", "이메일,지원직군,지원사유,참여가능 요일,포트폴리오 링크", 7, "mobile-app-thumbnail.png", "Mobile",
                        "Android 스튜디오를 사용하여 앱을 개발합니다.", "코드 품질을 유지해야 합니다.", 'N'),

                new GroupPostEntity(
                        "클라우드 인프라 구축 프로젝트", users.get(7), GroupType.PROJECT, LocalDate.now().plusDays(7), LocalDate.now().plusMonths(5),
                        LocalDate.now(), LocalDate.now().plusDays(10), "AWS 클라우드 인프라 구축 및 운영",
                        "클라우드", "이메일,지원직군,지원사유,다룰 수 있는 언어,참여가능 요일,자기소개,포트폴리오 링크,자유기재", 4, "cloud-infra-thumbnail.png", "Cloud",
                        "AWS 기반의 인프라를 설계하고 구축하는 프로젝트입니다.", "안전한 시스템 설계가 요구됩니다.", 'N')
        );

        groupPostRepository.saveAll(groupPosts); // groupPosts 리스트를 데이터베이스에 저장
        return groupPosts;
    }

    @Transactional
    public void insertProjectRecruitmentData(List<GroupPostEntity> groupPosts) {
        List<RecruitmentsEntity> recruitments = groupPosts.stream()
                .filter(post -> post.getType() == GroupType.PROJECT)  // 프로젝트 타입인지 확인
                .flatMap(post -> Stream.of(
                        new RecruitmentsEntity(post, RecruitRole.BACKEND, 3),
                        new RecruitmentsEntity(post, RecruitRole.FRONTEND, 2)
                        )
                ).toList();

        recruitmentsRepository.saveAll(recruitments);  // 모집 직군을 데이터베이스에 저장
    }

    @Transactional
    public List<RecruitApplyEntity> insertRecruitApplyData(List<GroupPostEntity> groupPosts, List<UserEntity> users) {
        List<RecruitApplyEntity> recruitApplies = groupPosts.stream()
                .flatMap(post -> users.stream().limit(3).map(user ->
                        new RecruitApplyEntity(
                                post, user, RecruitRole.BACKEND, user.getEmail(),
                                "이 프로젝트에 참여하고 싶은 이유는 성장 기회입니다.",
                                "개발 경력 3년, 다양한 프로젝트 경험",
                                "Java, Spring, React",
                                "https://portfolio-link.com",
                                "주 3일 참여 가능",
                                "추가적인 정보는 없습니다."
                        )
                )).toList();

        recruitApplyRepository.saveAll(recruitApplies);  // 지원서 데이터를 데이터베이스에 저장
        return recruitApplies;
    }

    @Transactional
    public void insertGroupMembers(List<RecruitApplyEntity> recruitApplies) {
        List<GroupMemberEntity> groupMembers = recruitApplies.stream()
                .map(recruitApply -> new GroupMemberEntity(
                        recruitApply.getGroupPost(), recruitApply.getUser(),
                        MemberRole.MEMBER, recruitApply.getPosition().name()))
                .toList();

        groupMemberRepository.saveAll(groupMembers);  // 그룹 멤버 데이터를 데이터베이스에 저장
    }

    @Transactional
    public void insertGroupLeaders(List<GroupPostEntity> groupPosts) {
        List<GroupMemberEntity> groupMemberEntities = groupPosts.stream()
                .map(groupPost -> new GroupMemberEntity(
                        groupPost, groupPost.getLeaderUser(),
                        MemberRole.LEADER, null))
                .toList();

        groupMemberRepository.saveAll(groupMemberEntities);

    }

    @Transactional
    public void insertGroupNewsData(List<GroupPostEntity> groupPosts) {
        List<GroupNewsEntity> groupNewsList = groupPosts.stream()
                .map(post -> new GroupNewsEntity(
                        post.getName() + "에 새로운 일정이 추가되었습니다.",
                        post
                ))
                .toList();

        groupNewsRepository.saveAll(groupNewsList);
    }

    @Transactional
    public void insertQuestionBoardData(List<GroupPostEntity> groupPosts, List<UserEntity> users) {
        List<QuestionBoardEntity> questionBoards = groupPosts.stream()
                .flatMap(post -> Stream.of(
                        new QuestionBoardEntity(
                                post, users.get(0), BoardType.QUESTION, LocalDateTime.now(),
                                "이 스터디의 목적은 무엇인가요?", null
                        ),
                        new QuestionBoardEntity(
                                post, users.get(1), BoardType.QUESTION, LocalDateTime.now(),
                                "과제 제출일은 언제인가요?", null
                        )
                )).toList();

        questionBoardRepository.saveAll(questionBoards);  // 질문 데이터를 데이터베이스에 저장

        // 질문에 대한 댓글 추가
        insertQuestionComments(questionBoards, users);
    }

    @Transactional
    public void insertQuestionComments(List<QuestionBoardEntity> questionBoards, List<UserEntity> users) {
        List<QuestionCommentEntity> questionComments = questionBoards.stream()
                .flatMap(question -> Stream.of(
                        new QuestionCommentEntity(
                                question, null, users.get(2), LocalDateTime.now(),
                                "스터디 시작일입니다.", null
                        ),
                        new QuestionCommentEntity(
                                question, null, users.get(3), LocalDateTime.now(),
                                "과제 마감일은 다음 주입니다.", null
                        )
                )).toList();

        questionCommentRepository.saveAll(questionComments);  // 댓글 데이터를 데이터베이스에 저장
    }
}
