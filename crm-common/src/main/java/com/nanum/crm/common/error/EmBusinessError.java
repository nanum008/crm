package com.nanum.crm.common.error;

// 业务异常
public enum EmBusinessError implements CommonError {
    USER_NOT_EXIST(3330001, "用户不存在"),
    USER_PASSWORD_DONT_MATCH(3330002, "密码不匹配"),
    USER_LOCK_STATE(3330003, "账户锁定"),
    USER_IP_DONT_MATCH(3330004, "IP受限"),
    USER_DONT_LOGIN(3330005, "用户还未登录"),
    UNKNOWN_ERROR(9999999, "未知错误");

    private final int errorCode;
    private String errorMsg;

    EmBusinessError(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "EmBusinessError{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }

    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }

    @Override
    public CommonError setErrorMsg(String msg) {
        this.errorMsg = msg;
        return this;
    }
}
