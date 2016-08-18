package com.tw.eshop.vo;

/**
 * Created by qbhuang on 16/8/18.
 */
public class ValidateResult {
    boolean valid;
    String msg;

    public ValidateResult(boolean valid) {
        this.valid = valid;
    }

    public ValidateResult(boolean valid, String msg) {
        this.valid = valid;
        this.msg = msg;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
