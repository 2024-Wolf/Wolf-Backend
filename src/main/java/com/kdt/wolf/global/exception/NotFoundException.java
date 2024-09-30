package com.kdt.wolf.global.exception;

import com.kdt.wolf.global.exception.code.ExceptionCode;
import com.kdt.wolf.global.exception.code.ResultCodeProvider;

public class NotFoundException extends CustomException {

    public NotFoundException() {
        super(ExceptionCode.NOT_FOUND);
    }

    public NotFoundException(ResultCodeProvider resultCode) {
        super(resultCode);
    }

    // 추가 데이터를 받을 수 있는 생성자
    public NotFoundException(ResultCodeProvider resultCode, Object data) {
        super(resultCode, data);
    }
}
