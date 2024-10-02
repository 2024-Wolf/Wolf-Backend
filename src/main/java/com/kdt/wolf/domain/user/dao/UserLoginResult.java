package com.kdt.wolf.domain.user.dao;

import com.kdt.wolf.domain.user.dto.LoginFlag;
import com.kdt.wolf.domain.user.entity.UserEntity;

public record UserLoginResult (
        UserEntity user,
        LoginFlag flag
) { }