package com.kf.waqtiktok.mvvm.entity;

public class PayEntity {

    private Integer code;
    private DataBean data;
    private Boolean success;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private String orderinfo;

        public String getOrderinfo() {
            return orderinfo;
        }

        public void setOrderinfo(String orderinfo) {
            this.orderinfo = orderinfo;
        }
    }

}
