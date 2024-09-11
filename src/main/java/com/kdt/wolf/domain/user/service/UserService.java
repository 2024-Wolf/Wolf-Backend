package com.kdt.wolf.domain.user.service;

import com.kdt.wolf.domain.user.dto.response.UserProfileResponse;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.UserRepository;
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserProfileResponse getUserProfile(Long userId) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(ExceptionCode.USER_NOT_FOUND));
        return new UserProfileResponse(userEntity);
    }

    public UserProfileResponse insertTestUser() {

        UserEntity userEntity = UserEntity.makeTestUser();
        userRepository.save(userEntity);
        return new UserProfileResponse(userEntity);
    }
}
