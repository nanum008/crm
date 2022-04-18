package com.nanum.crm.common.error;

// 通用的错误
public interface CommonError {
    int getErrorCode();

    String getErrorMsg();

    CommonError setErrorMsg(String msg);
}
