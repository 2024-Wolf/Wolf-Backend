package com.kdt.wolf.global.exception.code;

public interface ResultCodeProvider {
    String getMessage();

    default String getCode() {
        if (this instanceof Enum<?>) {
            return ((Enum<?>) this).name();
        }

        return null;
    }
}
