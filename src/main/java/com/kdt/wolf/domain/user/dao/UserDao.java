package com.kdt.wolf.domain.user.dao;

import com.kdt.wolf.domain.report.service.ReportAction;
import com.kdt.wolf.domain.user.dto.LoginFlag;
import com.kdt.wolf.domain.user.dto.SignUpDto.SignUpRequest;
import com.kdt.wolf.domain.user.dto.UserAdminDto.UserDetailResponse;
import com.kdt.wolf.domain.user.dto.UserAdminDto.UserPreviewResponse;
import com.kdt.wolf.domain.user.entity.ActivityMetricsEntity;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.entity.common.Status;
import com.kdt.wolf.domain.user.info.OAuth2UserInfo;
import com.kdt.wolf.domain.user.info.impl.GoogleOAuth2UserInfo;
import com.kdt.wolf.domain.user.repository.ActivityMetricsRepository;
import com.kdt.wolf.domain.user.repository.UserRepository;
import com.kdt.wolf.global.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDao {
    private final UserRepository userRepository;

    public UserEntity findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserLoginResult signUpOrSignIn(OAuth2UserInfo userInfo) {
        Optional<UserEntity> user = userRepository.findByEmail(userInfo.getEmail());
        if(user.isPresent()) {
            return new UserLoginResult(user.get(), LoginFlag.LOGIN);
        }
        UserEntity newUser = userRepository.save(userInfo.toEntity());
        return new UserLoginResult(newUser, LoginFlag.SIGNUP);
    }

    @Transactional
    public void updateUser(long userId, SignUpRequest request) {
        UserEntity user = findById(userId);
        user.updateNickname(request.nickname());
        user.updateDetailProfile(
                request.jobTitle(),
                request.organization(),
                request.experience(),
                request.interests()
        );
        saveUser(user);
    }

    public void updateUser(UserEntity user) {
        saveUser(user);
    }

    @Transactional
    public Status changeUserStatus(Long userId, Status status) {
        UserEntity user = findById(userId);
        user.updateUserStatus(status);
        return saveUser(user).getStatus();
    }

    private UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    public List<UserPreviewResponse> findAllUserPreview() {
        return userRepository.findAll().stream()
                .map(UserEntity::toUserPreviewResponse)
                .toList();
    }

    public UserDetailResponse findUserDetail(Long userId) {
        UserEntity user = findById(userId);
        return user.toUserDetailResponse();
    }

    @Transactional
    public void suspendUser(UserEntity user) {
        user.suspend();
        saveUser(user);
    }

    @Transactional
    public Long activateUser(UserEntity user) {
        user.activate();
        return user.getUserId();
    }

    @Transactional
    public void banUser(UserEntity user) {
        user.ban();
    }

    @Transactional
    public Long panaltyUser(UserEntity user, ReportAction reportAction) {
        if(reportAction == ReportAction.SUSPEND) {
            suspendUser(user);
        } else if(reportAction == ReportAction.BAN) {
            banUser(user);
        }
        return user.getUserId();
    }
}
