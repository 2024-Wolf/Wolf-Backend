package com.kdt.wolf.global.exception;

import com.kdt.wolf.global.message.ResponseMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ApiException extends RuntimeException{
    private final ResponseMessage responseMessage;
}
