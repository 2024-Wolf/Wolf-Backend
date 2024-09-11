package com.kdt.wolf.domain.user.service;

import com.kdt.wolf.domain.user.dao.UserDao;
import com.kdt.wolf.domain.user.dto.response.UserProfileResponse;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.repository.UserRepository;
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserProfileResponse getUserProfile(Long userId) {
        return new UserProfileResponse(userDao.findById(userId));
    }

    public UserProfileResponse insertTestUser() {
        return new UserProfileResponse(userDao.saveTestUser());
    }
}
