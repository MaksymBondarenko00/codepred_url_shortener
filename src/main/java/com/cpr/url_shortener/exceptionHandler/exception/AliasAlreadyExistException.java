package com.cpr.url_shortener.exceptionHandler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AliasAlreadyExistException extends RuntimeException {
    public AliasAlreadyExistException(String message) {
        super(message);
    }
}
