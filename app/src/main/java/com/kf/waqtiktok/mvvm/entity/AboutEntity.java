package com.kf.waqtiktok.mvvm.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

public class AboutEntity implements CustomTabEntity {

    private  String title;

    public AboutEntity(String title) {
        this.title = title;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }
}
