package com.nanum.crm.common.response;

public class CommonResponse {
    // 处理状态：success、failed
    private String status;
    // 处理结果
    private Object data;

    private CommonResponse() {
    }

    public static CommonResponse create(Object data) {

        return CommonResponse.create("Success", data);
    }

    public static CommonResponse create(String status, Object data) {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setStatus(status);
        commonResponse.setData(data);
        return commonResponse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
