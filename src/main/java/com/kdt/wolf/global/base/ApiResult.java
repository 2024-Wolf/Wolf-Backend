package com.kdt.wolf.global.base;

import com.kdt.wolf.global.exception.CustomException;
import com.kdt.wolf.global.exception.code.ResultCodeProvider;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
@Builder
public class ApiResult<T> {
    private String code;
    private String message;
    private T data;
    private HttpStatus status;  // HTTP 상태 코드를 추가

    public static ApiResult<Object> of(final CustomException exception) {
        return of(exception.getResultCode(), exception.getData());
    }

    public static ApiResult<Object> of(final Exception e) {
        return of(e.getMessage(), e.getMessage(), null);
    }

    public static <T> ApiResult<T> of(final ResultCodeProvider resultCode, final T data) {
        return ApiResult.<T>builder()
                .code(resultCode.getCode())
                .message(resultCode.getMessage())
                .data(data)
                .build();
    }

    public static <T> ApiResult<T> of(final String code, final String message, final T data, final HttpStatus status) {
        return ApiResult.<T>builder()
                .code(code)
                .message(message)
                .data(data)
                .status(status)
                .build();
    }

    public static ApiResult<?> ok() {
        return ok(null);
    }

    public static <T> ApiResult<T> ok(T data) {
        return of("SUCCESS_NORMAL", "성공", data);
    }

    public static <T> ApiResult<T> created(T data) {
        return of("CREATED", "자원이 성공적으로 생성되었습니다.", data, HttpStatus.CREATED);
    }

    public static ApiResult<String> badRequest(String message) {
        return of("BAD_REQUEST", message, null);
    }

    public static ApiResult<List<ValidationErrorResponse>> badRequest(
            List<ValidationErrorResponse> validationErrors) {
        return of("INVALIDATE_REQUEST", "요청값이 잘못되었습니다.", validationErrors);
    }

    private static <T> ApiResult<T> of(final String code, final String message, final T data) {
        return ApiResult.<T>builder().code(code).message(message).data(data).build();
    }
}
