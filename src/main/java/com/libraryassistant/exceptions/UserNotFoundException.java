package com.libraryassistant.exceptions;

public class UserNotFoundException extends BaseException {

    @Override
    public String getErrorCode() {
        return "Читателя с таким идентификатором не обнаружено";
    }

}
