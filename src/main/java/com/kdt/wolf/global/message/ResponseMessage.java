package com.kdt.wolf.global.message;

import org.springframework.http.HttpStatus;


public interface ResponseMessage {
    String getMessage();
    HttpStatus getStatus();
}
