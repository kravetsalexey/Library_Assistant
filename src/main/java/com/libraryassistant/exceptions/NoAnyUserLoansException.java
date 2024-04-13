package com.libraryassistant.exceptions;

import org.springframework.http.HttpStatus;

public class NoAnyUserLoansException extends BaseException {

    private final HttpStatus status = HttpStatus.NOT_FOUND;

    @Override
    public String getErrorCode() {
        return "Читатель не взял ни одной книги";
    }
}
