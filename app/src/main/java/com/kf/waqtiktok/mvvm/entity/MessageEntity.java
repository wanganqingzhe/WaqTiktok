package com.kf.waqtiktok.mvvm.entity;

public class MessageEntity {

    private  String icon;  //用户头像
    private   String username;  //用户名
    private  String msg; //消息

    public MessageEntity(String icon, String username, String msg) {
        this.icon = icon;
        this.username = username;
        this.msg = msg;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
