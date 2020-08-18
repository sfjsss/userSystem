package com.tianyuli.usersystem.rpcDomain.common.strategy;

public enum OperatorStrategyEnum {
    SUCCESS("SUCCESS", "successful"),
    FAIL("FAIL", "fail"),
    UNKNOWN("UNKNOWN", "unknown"),
    EMAIL_FAIL("EMAIL_FAIL", "captcha sent failed");

    /**
     * operation code
     */
    private String code;

    /**
     * description
     */
    private String desc;

    OperatorStrategyEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
