package com.ecomercebackend.ecomercebackend.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EcommerceExceptionHandler {

    @ExceptionHandler(value = EcommerceApplicationException.class)
    public ResponseEntity<Object> exception(EcommerceApplicationException exception) {
        return new ResponseEntity<>(exception, exception.getStatus());
    }
}
