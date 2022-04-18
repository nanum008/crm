package com.nanum.crm.web.controller;

import com.nanum.crm.common.Constant;
import com.nanum.crm.common.error.BusinessException;
import com.nanum.crm.common.error.EmBusinessError;
import com.nanum.crm.common.response.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    //定义Exception解决未被controller层处理的异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handleException(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        Map<String, Object> errorInfo = new HashMap<>();
        if (e instanceof BusinessException) {
            BusinessException businessException = (BusinessException) e;
            errorInfo.put("errorCode", businessException.getErrorCode());
            errorInfo.put("errorMsg", businessException.getErrorMsg());
        } else {
            errorInfo.put("errorCode", EmBusinessError.UNKNOWN_ERROR.getErrorCode());
            errorInfo.put("errorMsg", EmBusinessError.UNKNOWN_ERROR.getErrorMsg());
        }
        return CommonResponse.create(Constant.HANDLE_FAIL,errorInfo);
    }
}
