package com.libraryassistant.exceptions;

import org.springframework.http.HttpStatus;

public class NoAnyBookLoansException extends BaseException{

    private final HttpStatus status = HttpStatus.NOT_FOUND;

    @Override
    public String getErrorCode() {
        return "Нет ни одной взятой книги";
    }
}
