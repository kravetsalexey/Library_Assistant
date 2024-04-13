package com.libraryassistant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BaseException extends RuntimeException {

    private  String errorCode;
    private final HttpStatus status = HttpStatus.NOT_FOUND;




    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    }
