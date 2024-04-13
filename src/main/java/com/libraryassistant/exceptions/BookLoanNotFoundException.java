package com.libraryassistant.exceptions;

import org.springframework.http.HttpStatus;

public class BookLoanNotFoundException extends BaseException {

    private final HttpStatus status = HttpStatus.NOT_FOUND;

    @Override
    public String getErrorCode() {
        return "Записи о взятии книги с таким идентификатором не обнаружено";
    }
}
