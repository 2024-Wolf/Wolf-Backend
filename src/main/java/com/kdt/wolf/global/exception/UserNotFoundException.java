package com.kdt.wolf.global.exception;

import com.kdt.wolf.global.exception.code.ResultCodeProvider;
import lombok.Getter;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super(new UserNotFoundCode());
    }

    @Getter
    public static class UserNotFoundCode implements ResultCodeProvider {
        private final String code = "USER_NOT_FOUND";
        private final String message = "해당 유저를 찾을 수 없습니다.";
    }
}
