package com.kdt.wolf.domain.user.service;

import static org.junit.jupiter.api.Assertions.*;

import com.kdt.wolf.domain.user.dao.UserDao;
import com.kdt.wolf.domain.user.dto.SignUpDto.SignUpRequest;
import com.kdt.wolf.domain.user.dto.UserAdminDto.UserDetailResponse;
import com.kdt.wolf.domain.user.dto.UserDto.UserProfileDetailResponse;
import com.kdt.wolf.domain.user.dto.UserDto.UserProfileResponse;
import com.kdt.wolf.domain.user.dto.UserDto.UserUpdateRequest;
import com.kdt.wolf.domain.user.entity.ActivityMetricsEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.entity.common.Status;
import com.kdt.wolf.domain.user.info.OAuth2UserInfo;
import com.kdt.wolf.domain.user.info.impl.GoogleOAuth2UserInfo;
import com.kdt.wolf.domain.user.repository.ActivityMetricsRepository;
import com.kdt.wolf.domain.user.repository.UserRepository;
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ActivityMetricsRepository activityMetricsRepository;
    private UserEntity userEntity;
    private ActivityMetricsEntity activityMetricsEntity;

    @BeforeEach
    void setUp() {
        OAuth2UserInfo userInfo = new GoogleOAuth2UserInfo(
                "googleId",
                "name",
                "nickname",
                "email",
                "picture"
        );

        userEntity = userDao.signUpOrSignIn(userInfo).user();
        activityMetricsEntity = userEntity.getActivityMetrics();
    }

    @Test
    void getUserProfileDetail() {
        UserProfileDetailResponse response = userService.getUserProfileDetail(userEntity.getUserId());
        assertNotNull(response);
        assertEquals(userEntity.getNickname(), response.nickname());
        assertEquals(userEntity.getEmail(), response.email());
    }

    @Test
    void getUserProfile() {
        UserProfileResponse response = userService.getUserProfile(userEntity.getUserId());
        assertNotNull(response);
        assertEquals(userEntity.getNickname(), response.nickname());
    }

    @Test
    void completeSignUpProcess() {
        SignUpRequest request = new SignUpRequest(
                "jobTitle",
                "organization",
                1,
                "interests",
                "Nickname"
        );
        userService.completeSignUpProcess(userEntity.getUserId(), request);

        UserEntity updatedUser = userRepository.findById(userEntity.getUserId()).orElseThrow();
        assertEquals("Nickname", updatedUser.getNickname());
        assertEquals("jobTitle", updatedUser.getJobTitle());
    }

    @Test
    void updateMyProfile() {
        UserUpdateRequest request = new UserUpdateRequest(
                userEntity.getUserId(),
                "newNickname",
                "newName",
                "newJobTitle",
                "newOrganization",
                1,
                "newInterests",
                "newRefundAccount",
                "newIntroduction"
        );
        UserProfileDetailResponse response = userService.updateMyProfile(userEntity.getUserId(), request);

        assertNotNull(response);
        assertEquals("newNickname", response.nickname());
        assertEquals("email", response.email());
    }

    @Test
    void updateMyProfile_AccessDenied() {
        UserUpdateRequest request = new UserUpdateRequest(
                userEntity.getUserId(),
                "newNickname",
                "newName",
                "newJobTitle",
                "newOrganization",
                1,
                "newInterests",
                "newRefundAccount",
                "newIntroduction"
        );

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userService.updateMyProfile(999L, request); // 다른 유저 ID로 접근
        });

        assertEquals(ExceptionCode.ACCESS_DENIED, exception.getResultCode());
    }

    @Test
    void getUserList() {
        List<?> userList = userService.getUserList();
        assertNotNull(userList);
        assertFalse(userList.isEmpty());
    }

    @Test
    void getUserDetail() {
        UserDetailResponse response = userService.getUserDetail(userEntity.getUserId());
        assertNotNull(response);
        assertEquals(userEntity.getNickname(), response.nickname());
    }

    @Test
    void warningUser() {
        // warning 기능이 구현되어야 테스트 가능
    }

    @Test
    void banUser() {
        Status status = userService.banUser(userEntity.getUserId());
        assertEquals(Status.BANNED, status);

        UserEntity bannedUser = userRepository.findById(userEntity.getUserId()).orElseThrow();
        assertEquals(Status.BANNED, bannedUser.getStatus());
    }

    @Test
    void unbanUser() {
        userService.banUser(userEntity.getUserId()); // 먼저 ban 처리

        Status status = userService.unbanUser(userEntity.getUserId());
        assertEquals(Status.ACTIVE, status);

        UserEntity unbannedUser = userRepository.findById(userEntity.getUserId()).orElseThrow();
        assertEquals(Status.ACTIVE, unbannedUser.getStatus());
    }

    @Test
    void suspendUser() {
        Status status = userService.suspendUser(userEntity.getUserId());
        assertEquals(Status.SUSPENDED, status);

        UserEntity suspendedUser = userRepository.findById(userEntity.getUserId()).orElseThrow();
        assertEquals(Status.SUSPENDED, suspendedUser.getStatus());
    }
}
