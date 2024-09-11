package com.kdt.wolf.global.exception;


import com.kdt.wolf.global.exception.code.ExceptionCode;

public class BusinessException extends CustomException {

    public BusinessException(ExceptionCode resultCode) {
        super(resultCode);
    }

    public BusinessException(ExceptionCode resultCode, Object data) {
        super(resultCode, data);
    }
}
