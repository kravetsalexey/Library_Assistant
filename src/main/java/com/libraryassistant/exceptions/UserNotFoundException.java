package com.libraryassistant.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {

    private final HttpStatus status = HttpStatus.NOT_FOUND;
    @Override
    public String getErrorCode() {
        return "Читателя с таким идентификатором не обнаружено";
    }

}
