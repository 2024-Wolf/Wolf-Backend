package com.kdt.wolf.domain.user.service;

import com.kdt.wolf.domain.user.dao.AlertDao;
import com.kdt.wolf.domain.user.dao.UserDao;
import com.kdt.wolf.domain.user.dto.FcmDto.AlertDto;
import com.kdt.wolf.domain.user.dto.SignUpDto.SignUpRequest;
import com.kdt.wolf.domain.user.dto.UserDto.UserProfileDetailResponse;
import com.kdt.wolf.domain.user.dto.UserDto.UserProfileResponse;
import com.kdt.wolf.domain.user.dto.UserDto.UserUpdateRequest;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;
    private final AlertDao alertDao;

    public UserProfileDetailResponse getUserProfileDetail(Long userId) {
        UserEntity user = userDao.findById(userId);
        return user.toUserProfileDetailResponse();
    }

    public UserProfileResponse getUserProfile(Long userId) {
        UserEntity user = userDao.findById(userId);
        return user.toUserProfileResponse();
    }

    public void completeSignUpProcess(long userId, SignUpRequest request) {
        userDao.updateUser(userId, request);
    }


    public UserProfileDetailResponse updateMyProfile(Long userId, UserUpdateRequest request) {
        if(userId != request.id()) {
            throw new BusinessException(ExceptionCode.ACCESS_DENIED);
        }
        UserEntity user = userDao.findById(userId);
        userDao.updateUser(user.updateProfile(request));
        return user.toUserProfileDetailResponse();
    }

    public List<AlertDto> getAlarms(Long userId) {
        return alertDao.getAlarms(userId);
    }
}
