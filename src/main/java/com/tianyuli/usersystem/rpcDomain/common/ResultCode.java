package com.tianyuli.usersystem.rpcDomain.common;

import java.io.Serializable;

public enum ResultCode implements Serializable {

    WRONG_CAPTCHA(20024, "incorrect captcha"),
    REGISTER_RECORD_IS_EMPTY(-2004, "no record found"),
    REGISTERED_SUCCESS(20023, "registered success");

    int code;

    String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
