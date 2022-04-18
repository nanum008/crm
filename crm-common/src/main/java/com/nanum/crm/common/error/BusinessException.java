package com.nanum.crm.common.error;

public class BusinessException extends Exception implements CommonError {

    private final CommonError commonError;

    public BusinessException(String message, CommonError commonError) {
        super();
        this.commonError = commonError;
        this.commonError.setErrorMsg(message);
    }

    public BusinessException( CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getErrorMsg() {
        return this.commonError.getErrorMsg();
    }

    @Override
    public CommonError setErrorMsg(String msg) {
        return this.commonError.setErrorMsg(msg);
    }
}
