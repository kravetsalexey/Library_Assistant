package com.libraryassistant.exceptions;

public class EntityAlreadyExistsException extends BaseException{

    @Override
    public String getErrorCode() {
        return "Сущность с такими данными уже зарегистрирована";
    }

}
