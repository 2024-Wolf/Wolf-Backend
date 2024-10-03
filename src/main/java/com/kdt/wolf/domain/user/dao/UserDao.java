package com.kdt.wolf.domain.user.dao;

import com.kdt.wolf.domain.user.dto.LoginFlag;
import com.kdt.wolf.domain.user.dto.SignUpDto.SignUpRequest;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.entity.common.Status;
import com.kdt.wolf.domain.user.info.OAuth2UserInfo;
import com.kdt.wolf.domain.user.info.impl.GoogleOAuth2UserInfo;
import com.kdt.wolf.domain.user.repository.UserRepository;
import com.kdt.wolf.global.exception.UserNotFoundException;
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
        return userRepository.findByEmail(userInfo.getEmail())
                .map(u -> new UserLoginResult(u, LoginFlag.LOGIN))
                .orElseGet(() -> new UserLoginResult(userRepository.save(userInfo.toEntity()), LoginFlag.SIGNUP));
    }

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

    public Status changeUserStatus(Long userId) {
        UserEntity user = findById(userId);
        user.changeStatus(Status.WITHDRAWN);
        return saveUser(user).getStatus();
    }

    private UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }
}
