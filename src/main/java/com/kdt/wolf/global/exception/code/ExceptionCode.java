package com.kdt.wolf.global.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode implements ResultCodeProvider {
    LOGIN_FAILED("로그인에 실패하였습니다."),
    CAN_NOT_DELETE("삭제할 수 없습니다."),
    ACCESS_DENIED("권한이 없습니다."),
    VALIDATION_FAILED("유효성 검사에 실패하였습니다."),
    REFRESH_TOKEN_VALIDATION_FAILED("리프레시 토큰 검증에 실패하였습니다."),
    ID_TOKEN_VALIDATION_FAILED("ID 토큰 검증에 실패하였습니다."),
    NOT_FOUND("찾을 수 없습니다."),
    ALERT_SAVE_FAIL("알림 저장에 실패하였습니다."),
    FCM_SEND_FAIL("FCM 전송에 실패하였습니다."),;
    private final String message;
}
