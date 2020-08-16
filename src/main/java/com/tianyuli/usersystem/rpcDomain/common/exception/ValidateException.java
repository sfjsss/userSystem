package com.tianyuli.usersystem.rpcDomain.common.exception;

import com.tianyuli.usersystem.rpcDomain.common.ResultCode;

import java.text.MessageFormat;

public class ValidateException extends RuntimeException {

    ResultCode resultCode;

    public ValidateException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public ValidateException(ResultCode resultCode, Object... args) {
        super(resultCode.getMessage());
        String message = MessageFormat.format(resultCode.getMessage(), args);
        resultCode.setMessage(message);
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
