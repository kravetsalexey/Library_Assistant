package com.libraryassistant.exceptions;

public class NoAnyUserLoansException extends BaseException {

    @Override
    public String getErrorCode() {
        return "Читатель не взял ни одной книги";
    }
}
