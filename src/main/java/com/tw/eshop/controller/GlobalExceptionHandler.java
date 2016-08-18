package com.tw.eshop.controller;

import com.tw.eshop.exception.InvalidDataException;
import com.tw.eshop.exception.NotExistsOrderException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by qbhuang on 16/8/18.
 */

//@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    @ExceptionHandler(NotExistsOrderException.class)
    public void handleNotExistsOrderException() {
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 404
    @ExceptionHandler(InvalidDataException.class)
    public void handleInvalidDataException() {
    }
}
