package com.libraryassistant.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {
    @ExceptionHandler({BaseException.class})
    public ResponseEntity<Object> handleRequestExceptions(
            BaseException ex, WebRequest request) {
        return new ResponseEntity<Object>(
                ex.getErrorCode(), new HttpHeaders(), ex.getStatus());
    }

    @ExceptionHandler({InvalidEmailFormatException.class})
    public ResponseEntity<Object> handInvalidEmailFormatException(
            InvalidEmailFormatException ex, WebRequest request) {
        return new ResponseEntity<>(
                ex.getErrorCode(), new HttpHeaders(), ex.getStatus());
    }
}