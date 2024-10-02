package com.kdt.wolf.domain.user.service;

import com.kdt.wolf.domain.user.dao.UserDao;
import com.kdt.wolf.domain.user.dto.SignUpDto;
import com.kdt.wolf.domain.user.dto.SignUpDto.SignUpRequest;
import com.kdt.wolf.domain.user.dto.response.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public UserProfileResponse getUserProfile(Long userId) {
        return new UserProfileResponse(userDao.findById(userId));
    }

    public void completeSignUpProcess(long userId, SignUpRequest request) {
        userDao.updateUser(userId, request);
    }
}
