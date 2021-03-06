package com.tw.eshop.order.vo;

/**
 * Created by qbhuang on 16/8/17.
 */
public class HttpResponse<T> {
    private Integer resultCode;
    private String message;
    private T data;

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
