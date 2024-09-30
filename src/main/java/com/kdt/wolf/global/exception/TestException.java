package com.kdt.wolf.global.exception;

import com.kdt.wolf.global.exception.code.ResultCodeProvider;
import lombok.Getter;

public class TestException extends CustomException {
    public TestException() {
        super(new TestCode());
    }

    @Getter
    public static class TestCode implements ResultCodeProvider {
        private final String code = "TEST_CODE";
        private final String message = "TEST_MESSAGE";
    }
}
