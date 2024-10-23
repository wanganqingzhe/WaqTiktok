package com.kf.waqtiktok;

import android.app.Application;

import com.kf.waqtiktok.utils.db.RoomDbUtils;

/**
 * 作者： 王安卿
 * 日期： 2024/8/25 19:29
 * 功能：  项目唯一
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化  room数据库
        RoomDbUtils.getInstance().init(this);


    }
}
