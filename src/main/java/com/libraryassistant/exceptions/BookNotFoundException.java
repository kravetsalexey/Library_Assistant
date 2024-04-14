package com.libraryassistant.exceptions;

public class BookNotFoundException extends BaseException {
        @Override
    public String getErrorCode() {
        return "Книги с таким идентификатором не обнаружено";
    }
}
