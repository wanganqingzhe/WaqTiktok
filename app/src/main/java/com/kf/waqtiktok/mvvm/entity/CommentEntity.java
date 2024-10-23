package com.kf.waqtiktok.mvvm.entity;

import java.util.List;

public class CommentEntity {


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
        private Integer childcount;
        private String createtime;
        private String icon;
        private Integer id;
        private String msg;
        private Integer parentid;
        private Integer rootid;
        private String tousername;
        private Integer type;
        private String username;

        public Integer getChildcount() {
            return childcount;
        }

        public void setChildcount(Integer childcount) {
            this.childcount = childcount;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Integer getParentid() {
            return parentid;
        }

        public void setParentid(Integer parentid) {
            this.parentid = parentid;
        }

        public Integer getRootid() {
            return rootid;
        }

        public void setRootid(Integer rootid) {
            this.rootid = rootid;
        }

        public String getTousername() {
            return tousername;
        }

        public void setTousername(String tousername) {
            this.tousername = tousername;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
