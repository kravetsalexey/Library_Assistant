package com.libraryassistant.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BaseException extends RuntimeException {

    private  String errorCode;
    private final HttpStatus status = HttpStatus.NOT_FOUND;


}
