package com.kdt.wolf.global.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserMessage implements ResponseMessage {
    USER_NOT_FOUND("사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    USER_FOUND_SUCCESS("사용자 조회에 성공했습ㅣ다", HttpStatus.OK);

    private final String message;
    private final HttpStatus status;
}
