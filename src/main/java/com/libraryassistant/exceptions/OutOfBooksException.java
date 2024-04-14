package com.libraryassistant.exceptions;

public class OutOfBooksException extends BaseException{

    @Override
    public String getErrorCode() {
        return "В библиотеке больше нет экземпляров данной книги";
    }

}
