package com.kdt.wolf.domain.group.service;

import com.kdt.wolf.domain.group.dto.request.GroupPostRequest;
import com.kdt.wolf.domain.group.dto.response.GroupPostResponse;
import com.kdt.wolf.domain.group.entity.common.GroupType;
import com.kdt.wolf.domain.group.repository.GroupPostRepository;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.entity.common.SocialType;
import com.kdt.wolf.domain.user.entity.common.Status;
import com.kdt.wolf.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@Transactional
public class GroupPostServiceIntTest {
    @Autowired
    GroupPostService groupPostService;

    @Autowired
    GroupPostRepository groupPostRepository;

    @Autowired
    UserRepository userRepository; // UserRepository 추가

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

        // 그룹 모집 요청 객체 생성 및 저장
        GroupPostRequest studyRequest = GroupPostRequest.builder()
                .name("Study Group")
                .leaderUser(savedLeaderUser)
                .type(GroupType.STUDY)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .recruitStartDate(LocalDate.now())
                .recruitDeadlineDate(LocalDate.now().plusDays(7))
                .shortIntro("Short introduction")
                .tag("study")
                .optionalRequirements("No specific requirements")
                .targetMembers(10)
                .thumbnail("thumbnail_url")
                .title("Study Group Title")
                .description("Detailed description of the study group.")
                .warning("Warning message.")
                .challengeStatus('O') // 또는 'X'
                .build();

        GroupPostRequest projectRequest = GroupPostRequest.builder()
                .name("Project Group")
                .leaderUser(savedLeaderUser)
                .type(GroupType.PROJECT) // GroupType은 PROJECT
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .recruitStartDate(LocalDate.now())
                .recruitDeadlineDate(LocalDate.now().plusDays(7))
                .shortIntro("Short introduction for project group")
                .tag("project")
                .optionalRequirements("Specific skills needed for the project")
                .targetMembers(5)
                .thumbnail("project_thumbnail_url")
                .title("Project Group Title")
                .description("Detailed description of the project group.")
                .warning("Project group warning message.")
                .challengeStatus('X') // 또는 'O'
                .build();

        groupPostService.createPost(studyRequest);
        groupPostService.createPost(projectRequest);

        // When: 옵션에 따라 모집 글 조회
        List<GroupPostResponse> allPosts = groupPostService.getPostsByOption("all");
        List<GroupPostResponse> studyPosts = groupPostService.getPostsByOption("study");
        List<GroupPostResponse> projectPosts = groupPostService.getPostsByOption("project");

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
                .leaderUser(savedLeaderUser)
                .type(GroupType.STUDY)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .recruitStartDate(LocalDate.now())
                .recruitDeadlineDate(LocalDate.now().plusDays(7))
                .shortIntro("Short introduction")
                .tag("study")
                .optionalRequirements("No specific requirements")
                .targetMembers(10)
                .thumbnail("thumbnail_url")
                .title("Study Group Title")
                .description("Detailed description of the study group.")
                .warning("Warning message.")
                .challengeStatus('O') // 또는 'X'
                .build();

        GroupPostRequest projectRequest = GroupPostRequest.builder()
                .name("Project Group")
                .leaderUser(savedLeaderUser)
                .type(GroupType.PROJECT) // GroupType은 PROJECT
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(1))
                .recruitStartDate(LocalDate.now())
                .recruitDeadlineDate(LocalDate.now().plusDays(7))
                .shortIntro("Short introduction for project group")
                .tag("project")
                .optionalRequirements("Specific skills needed for the project")
                .targetMembers(5)
                .thumbnail("project_thumbnail_url")
                .title("Project Group Title")
                .description("Detailed description of the project group.")
                .warning("Project group warning message.")
                .challengeStatus('X') // 또는 'O'
                .build();

        groupPostService.createPost(studyRequest);
        groupPostService.createPost(projectRequest);

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


}
