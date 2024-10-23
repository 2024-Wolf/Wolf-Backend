package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dto.Recruitments;
import com.kdt.wolf.domain.group.dto.request.GroupPostRequest;
import com.kdt.wolf.domain.group.dto.request.RecruitApplyRequest;
import com.kdt.wolf.domain.group.dto.response.GroupMemberResponse;
import com.kdt.wolf.domain.group.dto.response.GroupPostResponse;
import com.kdt.wolf.domain.group.dto.response.QuestionResponse;
import com.kdt.wolf.domain.group.entity.*;
import com.kdt.wolf.domain.group.entity.common.BoardType;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import com.kdt.wolf.domain.group.entity.common.MemberRole;
import com.kdt.wolf.domain.group.entity.common.RecruitRole;
import com.kdt.wolf.domain.group.repository.*;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.entity.common.SocialType;
import com.kdt.wolf.domain.user.entity.common.Status;
import com.kdt.wolf.domain.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class GroupPostServiceIntTest {
    @Autowired
    GroupPostService groupPostService;

    @Autowired
    GroupPostRepository groupPostRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RecruitApplyService recruitApplyService;

    @Autowired
    RecruitApplyRepository recruitApplyRepository;

    @Autowired
    GroupMemberService groupMemberService;

    @Autowired
    GroupMemberRepository groupMemberRepository;

    @Autowired
    private QuestionBoardService questionBoardService;

    @Autowired
    private QuestionBoardRepository questionBoardRepository;

    @Autowired
    private QuestionCommentRepository questionCommentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void getPostsByType() {
        // Given: 유저 생성 및 DB에 저장
        UserEntity leaderUser = UserEntity.builder()
                .nickname("Leader")
                .name("User Name")
                .email("user@example.com")
                .profilePicture("profile_pic_url")
                .socialType(SocialType.KAKAO) // 예시로 KAKAO 사용
                .status(Status.ACTIVE) // 예시로 ACTIVE 상태 사용
                .build();

        UserEntity savedLeaderUser = userRepository.save(leaderUser);

        GroupPostRequest studyRequest = GroupPostRequest.builder()
                .name("Study Group")
                .type("study")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .recruitStartDate(LocalDate.now())
                .recruitDeadlineDate(LocalDate.now().plusDays(7))
                .shortIntro("Short introduction")
                .tag("study")
                .optionalRequirements("No specific requirements")
                .targetMembers(10)
                .thumbnail("thumbnail_url")
                .topic("Study Group Title")
                .description("Detailed description of the study group.")
                .warning("Warning message.")
                .challengeStatus('O')
                .build();

        // Recruitment 객체를 생성 (임시 데이터 예시)
        Recruitments recruitment1 = Recruitments.builder()
                .recruitRole(RecruitRole.BACKEND)
                .recruitRoleCnt(3)
                .build();

        Recruitments recruitment2 = Recruitments.builder()
                .recruitRole(RecruitRole.FRONTEND)
                .recruitRoleCnt(2)
                .build();

        List<Recruitments> recruitmentsList = Arrays.asList(recruitment1, recruitment2);

        GroupPostRequest projectRequest = GroupPostRequest.builder()
                .name("Project Group")
                .type("project")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .recruitStartDate(LocalDate.now())
                .recruitDeadlineDate(LocalDate.now().plusDays(7))
                .shortIntro("Short introduction for project group")
                .tag("project")
                .optionalRequirements("Specific skills needed for the project")
                .targetMembers(5)
                .thumbnail("project_thumbnail_url")
                .topic("Project Group Title")
                .description("Detailed description of the project group.")
                .warning("Project group warning message.")
                .challengeStatus('X') // 또는 'O'
                .recruitments(recruitmentsList) // recruitments 리스트 추가
                .build();


        groupPostService.createPost(studyRequest, savedLeaderUser.getUserId());
        groupPostService.createPost(projectRequest, savedLeaderUser.getUserId());

        // When: 옵션에 따라 모집 글 조회
        Pageable pageable = Pageable.ofSize(20);
        List<GroupPostResponse> allPosts = groupPostService.getPostsByType("all", pageable).groupPostResponseList();
        List<GroupPostResponse> studyPosts = groupPostService.getPostsByType("study", pageable).groupPostResponseList();
        List<GroupPostResponse> projectPosts = groupPostService.getPostsByType("project", pageable).groupPostResponseList();

        // Then: 조회된 모집 글 검증
        Assertions.assertEquals(1, studyPosts.size());
        Assertions.assertEquals("Study Group", studyPosts.get(0).getName());

        Assertions.assertEquals(1, projectPosts.size());
        Assertions.assertEquals("Project Group", projectPosts.get(0).getName());

        Assertions.assertEquals(2, allPosts.size());
        Assertions.assertEquals("Study Group", allPosts.get(0).getName());
        Assertions.assertEquals("Project Group", allPosts.get(1).getName());
    }

    @Test
    void searchPosts() {
        // Given: 유저 생성 및 DB에 저장
        UserEntity leaderUser = UserEntity.builder()
                .nickname("Leader")
                .name("User Name")
                .email("user@example.com")
                .profilePicture("profile_pic_url")
                .socialType(SocialType.KAKAO) // 예시로 KAKAO 사용
                .status(Status.ACTIVE) // 예시로 ACTIVE 상태 사용
                .build();

        UserEntity savedLeaderUser = userRepository.save(leaderUser);

        // 그룹 모집 요청 객체 생성 및 저장
        GroupPostRequest studyRequest = GroupPostRequest.builder()
                .name("Study Group")
                .type("study")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .recruitStartDate(LocalDate.now())
                .recruitDeadlineDate(LocalDate.now().plusDays(7))
                .shortIntro("Short introduction")
                .tag("study")
                .optionalRequirements("No specific requirements")
                .targetMembers(10)
                .thumbnail("thumbnail_url")
                .topic("Study Group Title")
                .description("Detailed description of the study group.")
                .warning("Warning message.")
                .challengeStatus('O') // 또는 'X'
                .build();

        GroupPostRequest projectRequest = GroupPostRequest.builder()
                .name("Project Group")
                .type("study")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .recruitStartDate(LocalDate.now())
                .recruitDeadlineDate(LocalDate.now().plusDays(7))
                .shortIntro("Short introduction for project group")
                .tag("project")
                .optionalRequirements("Specific skills needed for the project")
                .targetMembers(5)
                .thumbnail("project_thumbnail_url")
                .topic("Project Group Title")
                .description("Detailed description of the project group.")
                .warning("Project group warning message.")
                .challengeStatus('X') // 또는 'O'
                .build();

        groupPostService.createPost(studyRequest, savedLeaderUser.getUserId());
        groupPostService.createPost(projectRequest, savedLeaderUser.getUserId());

        // When: 검색 기능을 통해 모집 글 조회
        List<GroupPostResponse> searchStudyPosts = groupPostService.searchPosts("Study");
        List<GroupPostResponse> searchProjectPosts = groupPostService.searchPosts("Project");

        // Then: 조회된 모집 글 검증
        Assertions.assertEquals(1, searchStudyPosts.size());
        Assertions.assertEquals("Study Group", searchStudyPosts.get(0).getName());

        Assertions.assertEquals(1, searchProjectPosts.size());
        Assertions.assertEquals("Project Group", searchProjectPosts.get(0).getName());

        // Test for no results with a query that should not match
        List<GroupPostResponse> searchNoPosts = groupPostService.searchPosts("Non-existing Group");
        Assertions.assertTrue(searchNoPosts.isEmpty());
    }

    @Test
    void updatePost() {
        // Given: 유저 생성 및 DB에 저장
        UserEntity leaderUser = UserEntity.builder()
                .nickname("Leader")
                .name("User Name")
                .email("user@example.com")
                .profilePicture("profile_pic_url")
                .socialType(SocialType.KAKAO) // 예시로 KAKAO 사용
                .status(Status.ACTIVE) // 예시로 ACTIVE 상태 사용
                .build();

        UserEntity savedLeaderUser = userRepository.save(leaderUser);

        // 기존 그룹 포스트 생성 및 저장
        GroupPostRequest originalRequest = GroupPostRequest.builder()
                .name("Original Study Group")
                .type("study")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .recruitStartDate(LocalDate.now())
                .recruitDeadlineDate(LocalDate.now().plusDays(7))
                .shortIntro("Short introduction")
                .tag("study")
                .optionalRequirements("No specific requirements")
                .targetMembers(10)
                .thumbnail("thumbnail_url")
                .topic("Original Title")
                .description("Original description.")
                .warning("Original warning.")
                .challengeStatus('O') // 또는 'X'
                .build();

        groupPostService.createPost(originalRequest, savedLeaderUser.getUserId());

        GroupPostEntity savedGroupPost = groupPostRepository.findAll().get(0); // 저장된 그룹 포스트 가져오기

        // When: 그룹 포스트 수정 요청
        GroupPostRequest updateRequest = GroupPostRequest.builder()
                .topic("Updated Title") // 수정할 제목
                .description("Updated description.") // 수정할 설명
                .startDate(LocalDate.now().plusDays(2)) // 수정할 시작 날짜
                .endDate(LocalDate.now().plusDays(3)) // 수정할 종료 날짜
                .recruitStartDate(LocalDate.now().plusDays(1)) // 수정할 모집 시작 날짜
                .recruitDeadlineDate(LocalDate.now().plusDays(8)) // 수정할 모집 종료 날짜
                .targetMembers(12) // 수정할 목표 인원
                .optionalRequirements("Updated requirements") // 수정할 조건
                .thumbnail("updated_thumbnail_url") // 수정할 썸네일
                .tag("updated_study") // 수정할 태그
                .warning("Updated warning.") // 수정할 경고
                .build();

        groupPostService.editGroupPost(savedGroupPost.getGroupPostId(), updateRequest, savedLeaderUser.getUserId()); // 업데이트 요청 수행
        System.out.println(savedGroupPost.getGroupPostId());

        // Then: 수정된 그룹 포스트 검증
        GroupPostEntity updatedGroupPost = groupPostRepository.findById(savedGroupPost.getGroupPostId()).orElseThrow();
        Assertions.assertEquals("Updated Title", updatedGroupPost.getTopic());
        Assertions.assertEquals("Updated description.", updatedGroupPost.getDescription());
        Assertions.assertEquals(LocalDate.now().plusDays(2), updatedGroupPost.getStartDate());
        Assertions.assertEquals(LocalDate.now().plusDays(3), updatedGroupPost.getEndDate());
        Assertions.assertEquals(LocalDate.now().plusDays(1), updatedGroupPost.getRecruitStartDate());
        Assertions.assertEquals(LocalDate.now().plusDays(8), updatedGroupPost.getRecruitDeadlineDate());
        Assertions.assertEquals(12, updatedGroupPost.getTargetMembers());
        Assertions.assertEquals("Updated requirements", updatedGroupPost.getOptionalRequirements());
        Assertions.assertEquals("updated_thumbnail_url", updatedGroupPost.getThumbnail());
        Assertions.assertEquals("updated_study", updatedGroupPost.getTag());
        Assertions.assertEquals("Updated warning.", updatedGroupPost.getWarning());
    }

    @Test
    void deletePost() {
        // Given: 유저 생성 및 DB에 저장
        UserEntity leaderUser = UserEntity.builder()
                .nickname("Leader")
                .name("User Name")
                .email("user@example.com")
                .profilePicture("profile_pic_url")
                .socialType(SocialType.KAKAO) // 예시로 KAKAO 사용
                .status(Status.ACTIVE) // 예시로 ACTIVE 상태 사용
                .build();

        UserEntity savedLeaderUser = userRepository.save(leaderUser);

        // 그룹 모집 요청 객체 생성 및 저장
        GroupPostRequest studyRequest = GroupPostRequest.builder()
                .name("Study Group")
                .type("study")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .recruitStartDate(LocalDate.now())
                .recruitDeadlineDate(LocalDate.now().plusDays(7))
                .shortIntro("Short introduction")
                .tag("study")
                .optionalRequirements("No specific requirements")
                .targetMembers(10)
                .thumbnail("thumbnail_url")
                .topic("Study Group Title")
                .description("Detailed description of the study group.")
                .warning("Warning message.")
                .challengeStatus('O') // 또는 'X'
                .build();

        groupPostService.createPost(studyRequest, savedLeaderUser.getUserId()); // 포스트 생성

        // When: 그룹 포스트 삭제 요청
        List<GroupPostEntity> beforeDeletePosts = groupPostRepository.findAll();
        Assertions.assertEquals(1, beforeDeletePosts.size()); // 삭제 전 포스트 개수 확인

        Long groupPostId = beforeDeletePosts.get(0).getGroupPostId(); // 삭제할 포스트의 ID
        groupPostService.deleteGroupPost(groupPostId); // 포스트 삭제 요청 수행

        // Then: 삭제된 그룹 포스트가 DB에 존재하지 않음 확인
        List<GroupPostEntity> afterDeletePosts = groupPostRepository.findAll();
        Assertions.assertEquals(0, afterDeletePosts.size()); // 삭제 후 포스트 개수 확인
    }

    @Test
    void recruitApply() {
        // Given: 유저 생성 및 DB에 저장
        UserEntity leaderUser = UserEntity.builder()
                .nickname("Leader")
                .name("User Name")
                .email("leader@example.com")
                .profilePicture("profile_pic_url")
                .socialType(SocialType.KAKAO) // 예시로 KAKAO 사용
                .status(Status.ACTIVE) // 예시로 ACTIVE 상태 사용
                .build();

        UserEntity savedLeaderUser = userRepository.save(leaderUser);

        // 그룹 모집 요청 객체 생성 및 저장
        GroupPostRequest studyRequest = GroupPostRequest.builder()
                .name("Study Group")
                .type("study")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .recruitStartDate(LocalDate.now())
                .recruitDeadlineDate(LocalDate.now().plusDays(7))
                .shortIntro("Short introduction")
                .tag("study")
                .optionalRequirements("No specific requirements")
                .targetMembers(10)
                .thumbnail("thumbnail_url")
                .topic("Study Group Title")
                .description("Detailed description of the study group.")
                .warning("Warning message.")
                .challengeStatus('O') // 또는 'X'
                .build();

        groupPostService.createPost(studyRequest, savedLeaderUser.getUserId());

        GroupPostEntity savedGroupPost = groupPostRepository.findAll().get(0); // 저장된 그룹 포스트 가져오기

        // When: 지원 요청 객체 생성 및 지원 요청 수행
        RecruitApplyRequest applyRequest = RecruitApplyRequest.builder()
                .position("BACKEND")
                .email("applicant@example.com")
                .applicationReason("I am passionate about backend development and have experience in Spring Boot.")
                .introduction("I have a solid foundation in backend technologies and am eager to contribute.")
                .techStack("Java, Spring Boot, MySQL")
                .portfolioLink("https://portfolio-link.com")
                .availableDays("Monday to Friday")
                .additionalNotes("Excited to join and collaborate on challenging projects.")
                .build();

        recruitApplyService.recruitApply(savedGroupPost.getGroupPostId(), savedLeaderUser.getUserId(), applyRequest);

        RecruitApplyEntity savedApplication = recruitApplyRepository.findAll().get(0);
        // Then: 지원 성공 여부 확인
        Assertions.assertEquals(RecruitRole.valueOf(applyRequest.getPosition()), savedApplication.getPosition());
        Assertions.assertEquals(applyRequest.getEmail(), savedApplication.getEmail());
        Assertions.assertEquals(applyRequest.getApplicationReason(), savedApplication.getApplicationReason());
        Assertions.assertEquals(applyRequest.getIntroduction(), savedApplication.getIntroduction());
        Assertions.assertEquals(applyRequest.getTechStack(), savedApplication.getTechStack());
        Assertions.assertEquals(applyRequest.getPortfolioLink(), savedApplication.getPortfolioLink());
        Assertions.assertEquals(applyRequest.getAvailableDays(), savedApplication.getAvailableDays());
        Assertions.assertEquals(applyRequest.getAdditionalNotes(), savedApplication.getAdditionalNotes());
    }

    @Test
    void getGroupMembersTest() {
        // Given: 사용자 및 그룹 생성
        UserEntity leaderUser = UserEntity.builder()
                .nickname("Leader")
                .name("Leader Name")
                .email("leader@example.com")
                .profilePicture("leader_profile.png")
                .socialType(SocialType.KAKAO)
                .status(Status.ACTIVE)
                .build();
        userRepository.save(leaderUser);

        GroupPostEntity groupPost = GroupPostEntity.builder()
                .name("Study Group")
                .leaderUser(leaderUser)
                .type(GroupType.STUDY)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusWeeks(1))
                .recruitStartDate(LocalDate.now())
                .recruitDeadlineDate(LocalDate.now().plusDays(7))
                .shortIntro("Short intro")
                .tag("Study")
                .optionalRequirements("No requirements")
                .targetMembers(5)
                .thumbnail("thumbnail.png")
                .topic("Group Title")
                .description("Group Description")
                .warning("No warnings")
                .challengeStatus('O')
                .build();
        groupPostRepository.save(groupPost);

        GroupMemberEntity member1 = GroupMemberEntity.builder()
                .groupPost(groupPost)
                .user(leaderUser)
                .role(MemberRole.LEADER)
                .position("백엔드 개발자")
                .build();

        GroupMemberEntity member2 = GroupMemberEntity.builder()
                .groupPost(groupPost)
                .user(leaderUser)
                .role(MemberRole.MEMBER)
                .position("프론트엔드 개발자")
                .build();

        groupMemberRepository.save(member1);
        groupMemberRepository.save(member2);

        // When: 그룹 멤버 조회
        List<GroupMemberResponse> groupMembers = groupMemberService.getGroupMembers(groupPost.getGroupPostId());

        // Then: 그룹 멤버 수와 각 멤버의 정보 확인
        Assertions.assertEquals(2, groupMembers.size());

        GroupMemberResponse memberResponse1 = groupMembers.get(0);
        Assertions.assertEquals(MemberRole.LEADER, memberResponse1.getRole());
        Assertions.assertEquals("백엔드 개발자", memberResponse1.getPosition());

        GroupMemberResponse memberResponse2 = groupMembers.get(1);
        Assertions.assertEquals(MemberRole.MEMBER, memberResponse2.getRole());
        Assertions.assertEquals("프론트엔드 개발자", memberResponse2.getPosition());

    }

    @Test
    void getQuestionsWithComments() {
        // Given: 유저 생성 및 저장
        UserEntity author = UserEntity.builder()
                .nickname("User1")
                .name("User One")
                .email("user1@example.com")
                .profilePicture("user1_pic_url")
                .socialType(SocialType.KAKAO)
                .status(Status.ACTIVE)
                .build();

        userRepository.save(author);

        GroupPostEntity groupPost = GroupPostEntity.builder()
                .name("Study Group")
                .leaderUser(author)
                .type(GroupType.STUDY)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusWeeks(1))
                .recruitStartDate(LocalDate.now())
                .recruitDeadlineDate(LocalDate.now().plusDays(7))
                .shortIntro("Short intro")
                .tag("Study")
                .optionalRequirements("No requirements")
                .targetMembers(5)
                .thumbnail("thumbnail.png")
                .topic("Group Title")
                .description("Group Description")
                .warning("No warnings")
                .challengeStatus('O')
                .build();
        groupPostRepository.save(groupPost);

        // 질문 게시글 생성 및 저장
        QuestionBoardEntity question = QuestionBoardEntity.builder()
                .groupPost(groupPost)
                .user(author)
                .boardType(BoardType.QUESTION)
                .questionDetails("This is a test question.")
                .questionTime(LocalDateTime.now())
                .build();

        questionBoardRepository.save(question);

        // 질문 댓글 생성 및 저장
        QuestionCommentEntity comment1 = QuestionCommentEntity.builder()
                .question(question)
                .author(author)
                .commentDetails("This is a comment.")
                .createTime(LocalDateTime.now())
                .build();

        questionCommentRepository.save(comment1);

        QuestionCommentEntity comment2 = QuestionCommentEntity.builder()
                .parentComment(comment1)
                .question(question)
                .author(author)
                .commentDetails("This is another comment.")
                .createTime(LocalDateTime.now())
                .build();

        questionCommentRepository.save(comment2);

        // When: 질문 게시글과 댓글을 조회
        Pageable pageable = Pageable.ofSize(20);
        List<QuestionResponse> questionWithComments = questionBoardService.getQuestions(groupPost.getGroupPostId(), "question", pageable) .questionResponses();

        // Then: 검증
        Assertions.assertEquals(1, questionWithComments.size()); // 질문 하나 존재 확인
        Assertions.assertEquals(2, questionWithComments.get(0).getComments().size()); // 댓글 두 개 존재 확인
        Assertions.assertEquals("This is a test question.", questionWithComments.get(0).getQuestionDetails());
        Assertions.assertEquals("This is a comment.", questionWithComments.get(0).getComments().get(0).getCommentDetails());
        Assertions.assertEquals("This is another comment.", questionWithComments.get(0).getComments().get(1).getCommentDetails());
        Assertions.assertEquals(questionWithComments.get(0).getComments().get(0).getCommentId(), questionWithComments.get(0).getComments().get(1).getParentsId());

    }
}
