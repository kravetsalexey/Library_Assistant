package com.libraryassistant.exceptions;


public class MostPopularBookNotFoundException extends BaseException {


    @Override
    public String getErrorCode() {
        return "За данный период времени не взято ни одной книги";
    }
}
