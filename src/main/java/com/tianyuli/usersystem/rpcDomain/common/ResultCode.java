package com.tianyuli.usersystem.rpcDomain.common;

import java.io.Serializable;

public enum ResultCode implements Serializable {

    WRONG_CAPTCHA(20024, "incorrect captcha"),
    REGISTER_RECORD_IS_EMPTY(-2004, "no record found"),
    REGISTERED_SUCCESS(20023, "registered success"),
    PERMISSION_SIGNATURE_ERROR(70007, "signiture failed"),
    PERMISSION_TOKEN_EXPIRED(70004, "token expired"),
    PERMISSION_TOKEN_INVALID(70006, "invalid token"),
    USER_UN_VERIFIED(-2008, "non-existing user or unverified user"),
    WRONG_PASSWORD(-2009, "invalid credentials"),
    SUCCESS(0, "success"),
    USER_NOT_LOGGED_IN(20001, "user is not logged in"),
    SERVER_ERROR(-2011, "server error"),
    REG_DATA_IS_WRONG(-2001, "registration arg is wrong"),
    MAIL_SEND_FAIL(-2002, "mail send failed, please check if email is valid"),
    REGISTER_CAPTCHA_SEND(2001, "captcha sent"),
    PARAM_IS_BLANK(10002, "user param is blank"),
    USER_NOT_EXIST(20004, "user does not exist"),
    RESULT_DATA_NONE(50001, "data not found");

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
