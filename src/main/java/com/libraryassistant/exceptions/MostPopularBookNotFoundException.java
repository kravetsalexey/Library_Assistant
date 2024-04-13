package com.libraryassistant.exceptions;

import org.springframework.http.HttpStatus;

public class MostPopularBookNotFoundException extends BaseException {

    private final HttpStatus status = HttpStatus.NOT_FOUND;

    @Override
    public String getErrorCode() {
        return "За данный период времени не взято ни одной книги";
    }
}
