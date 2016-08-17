package com.tw.eshop.util;

/**
 * Created by qbhuang on 16/8/17.
 */
public class JsonConvertException extends RuntimeException {
    private Object content;
    private Class<?> contentClassType;

    public JsonConvertException(String message, Object content, Class<?> contentClassType) {
        super(message);
        this.content = content;
        this.contentClassType = contentClassType;
    }

    public JsonConvertException(String message, Throwable cause, Object content, Class<?> contentClassType) {
        super(message, cause);
        this.content = content;
        this.contentClassType = contentClassType;
    }

    public JsonConvertException(Throwable cause, Object content, Class<?> contentClassType) {
        super(cause);
        this.content = content;
        this.contentClassType = contentClassType;
    }
}