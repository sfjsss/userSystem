package com.tianyuli.usersystem.rpcDomain.common;

import java.io.Serializable;

public class RespResult<T> implements Serializable {

    private static final long serialVersionUID = 6869749443025009275L;

    int code;

    String message;

    T data;

    public RespResult() {
    }

    public RespResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public RespResult(ResultCode resultCode, T data) {
        this(resultCode);
        this.data = data;
    }

    public RespResult(String message) {
        this.message = message;
    }

    public static <T> RespResult SUCCESS(T data) {
        return new RespResult(ResultCode.SUCCESS, data);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
