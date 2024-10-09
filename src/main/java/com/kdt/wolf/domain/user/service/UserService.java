package com.kdt.wolf.domain.user.service;

import com.kdt.wolf.domain.user.dao.UserDao;
import com.kdt.wolf.domain.user.dto.SignUpDto.SignUpRequest;
import com.kdt.wolf.domain.user.dto.UserAdminDto.UserDetailResponse;
import com.kdt.wolf.domain.user.dto.UserAdminDto.UserPreviewResponse;
import com.kdt.wolf.domain.user.dto.UserDto.UserProfileDetailResponse;
import com.kdt.wolf.domain.user.dto.UserDto.UserProfileResponse;
import com.kdt.wolf.domain.user.dto.UserDto.UserUpdateRequest;
import com.kdt.wolf.domain.user.entity.UserEntity;
import com.kdt.wolf.domain.user.entity.common.Status;
import com.kdt.wolf.global.exception.BusinessException;
import com.kdt.wolf.global.exception.code.ExceptionCode;
import jakarta.transaction.Transactional;
import java.util.List;
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
        UserEntity user = userDao.findById(userId);
        return user.toUserProfileResponse();
    }

    public void completeSignUpProcess(long userId, SignUpRequest request) {
        userDao.updateUser(userId, request);
    }

    @Transactional
    public UserProfileDetailResponse updateMyProfile(Long userId, UserUpdateRequest request) {
        if(!userId.equals(request.id())) {
            throw new BusinessException(ExceptionCode.ACCESS_DENIED);
        }
        UserEntity user = userDao.findById(userId);
        userDao.updateUser(user.updateProfile(request));
        return user.toUserProfileDetailResponse();
    }

    public List<UserPreviewResponse> getUserList() {
        return userDao.findAllUserPreview();
    }

    public UserDetailResponse getUserDetail(Long userId) {
        return userDao.findUserDetail(userId);
    }

    public Long warningUser(Long userId) {
        //경고는 ?
        return null;
    }
    public Status banUser(Long userId) {
        return userDao.changeUserStatus(userId, Status.BANNED);
    }
    public Status unbanUser(Long userId) {
        return userDao.changeUserStatus(userId, Status.ACTIVE);
    }

    public Status suspendUser(Long userId) {
        Status status = userDao.changeUserStatus(userId, Status.SUSPENDED);
        if(status == Status.SUSPENDED) {
            userDao.suspendUser(userId);
        }
        return status;
    }

}
