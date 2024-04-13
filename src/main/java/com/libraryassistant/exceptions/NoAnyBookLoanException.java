package com.libraryassistant.exceptions;

import org.springframework.http.HttpStatus;

public class NoAnyBookLoanException extends BaseException{
    private final HttpStatus status = HttpStatus.NOT_FOUND;

    @Override
    public String getErrorCode() {
        return "Данную книгу ниразу не брали.";
    }
}
