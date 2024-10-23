package com.kf.waqtiktok.mvvm.entity;

import java.util.List;

/**
 * 作者： 王安卿
 * 日期： 2024/9/1 19:20
 * 功能：   礼物的实体类
 */

public class GiftEntity {


    private Integer code;
    private List<DataDTO> data;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataDTO {
        private String giftname;
        private String giftpath;
        private Integer id;
        private Integer price;

        public String getGiftname() {
            return giftname;
        }

        public void setGiftname(String giftname) {
            this.giftname = giftname;
        }

        public String getGiftpath() {
            return giftpath;
        }

        public void setGiftpath(String giftpath) {
            this.giftpath = giftpath;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }
    }
}
