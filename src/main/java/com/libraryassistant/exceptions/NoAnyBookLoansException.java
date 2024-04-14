package com.libraryassistant.exceptions;

public class NoAnyBookLoansException extends BaseException{

    @Override
    public String getErrorCode() {
        return "Нет ни одной взятой книги";
    }
}
