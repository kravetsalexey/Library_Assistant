package com.libraryassistant.exceptions;

import org.springframework.http.HttpStatus;

public class OutOfBooksException extends BaseException{

    private final HttpStatus status = HttpStatus.NOT_FOUND;

    @Override
    public String getErrorCode() {
        return "В библиотеке больше нет экземпляров данной книги";
    }

}
