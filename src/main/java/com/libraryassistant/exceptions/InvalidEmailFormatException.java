package com.libraryassistant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidEmailFormatException extends RuntimeException{

    HttpStatus status = HttpStatus.BAD_REQUEST;

    public HttpStatus getStatus() {
        return status;
    }

    public String getErrorCode(){return "Неправильно введён адрес электронной почты.";};
}
