package com.kf.waqtiktok.mvvm.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;
import java.util.List;

import io.reactivex.annotations.NonNull;


public class VideoEntity {


    private int code;
    private List<DataDTO> data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    @Entity(tableName = "video")
    public static class DataDTO implements MultiItemEntity, Serializable  {


        private String address;
        private String authname;
        private String caption;
        private int dianzan;
        private int guanzhu;
        private String headpath;
        @PrimaryKey
        private int id;
        private int like_count;
        private String publishtime;
        private int type;
        private String videomainimg;
        private String videopath;
        private int view_count;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAuthname() {
            return authname;
        }

        public void setAuthname(String authname) {
            this.authname = authname;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public int getDianzan() {
            return dianzan;
        }

        public void setDianzan(int dianzan) {
            this.dianzan = dianzan;
        }

        public int getGuanzhu() {
            return guanzhu;
        }

        public void setGuanzhu(int guanzhu) {
            this.guanzhu = guanzhu;
        }

        public String getHeadpath() {
            return headpath;
        }

        public void setHeadpath(String headpath) {
            this.headpath = headpath;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLike_count() {
            return like_count;
        }

        public void setLike_count(int like_count) {
            this.like_count = like_count;
        }

        public String getPublishtime() {
            return publishtime;
        }

        public void setPublishtime(String publishtime) {
            this.publishtime = publishtime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getVideomainimg() {
            return videomainimg;
        }

        public void setVideomainimg(String videomainimg) {
            this.videomainimg = videomainimg;
        }

        public String getVideopath() {
            return videopath;
        }

        public void setVideopath(String videopath) {
            this.videopath = videopath;
        }

        public int getView_count() {
            return view_count;
        }

        public void setView_count(int view_count) {
            this.view_count = view_count;
        }

        public int getItemType() {

            return type;
        }
    }
}
