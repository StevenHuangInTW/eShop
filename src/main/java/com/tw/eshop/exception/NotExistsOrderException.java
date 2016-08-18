package com.tw.eshop.exception;

/**
 * Created by qbhuang on 16/8/18.
 */
public class NotExistsOrderException extends RuntimeException {

    public NotExistsOrderException() {
        super();
    }

    public NotExistsOrderException(String message) {
        super(message);
    }

    public NotExistsOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistsOrderException(Throwable cause) {
        super(cause);
    }

    protected NotExistsOrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
