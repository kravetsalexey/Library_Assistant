package com.libraryassistant.exceptions;

public class NoAnyBookLoanException extends BaseException{

    @Override
    public String getErrorCode() {
        return "Данную книгу ниразу не брали.";
    }
}
