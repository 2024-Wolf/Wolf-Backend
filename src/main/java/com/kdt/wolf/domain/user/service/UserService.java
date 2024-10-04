package com.kdt.wolf.domain.user.service;

import com.kdt.wolf.domain.user.dao.UserDao;
import com.kdt.wolf.domain.user.dto.SignUpDto.SignUpRequest;
import com.kdt.wolf.domain.user.dto.UserDto.UserProfileDetailResponse;
import com.kdt.wolf.domain.user.dto.response.UserProfileResponse;
import com.kdt.wolf.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public UserProfileDetailResponse getUserProfileDetail(Long userId) {
        UserEntity user = userDao.findById(userId);
        return user.toUserProfileDetailResponse();
    }

    public UserProfileResponse getUserProfile(Long userId) {

        return new UserProfileResponse(userDao.findById(userId));
    }

    public void completeSignUpProcess(long userId, SignUpRequest request) {
        userDao.updateUser(userId, request);
    }


}
