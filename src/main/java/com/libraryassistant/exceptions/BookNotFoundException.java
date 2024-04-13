package com.libraryassistant.exceptions;

import org.springframework.http.HttpStatus;

public class BookNotFoundException extends BaseException {

    private final HttpStatus status = HttpStatus.NOT_FOUND;

    @Override
    public String getErrorCode() {
        return "Книги с таким идентификатором не обнаружено";
    }
}
