package com.kf.waqtiktok.utils.db;

import android.app.Application;

import androidx.room.Room;

public class RoomDbUtils {


    private static volatile RoomDbUtils roomDbUtils;

    private RoomDbUtils() {


    }

    public static RoomDbUtils getInstance() {

        if (roomDbUtils == null) {

            synchronized (RoomDbUtils.class) {

                if (roomDbUtils == null) {

                    roomDbUtils = new RoomDbUtils();


                }


            }


        }


        return roomDbUtils;

    }

    private TikTokDataBase tikTokDataBase;
    private VideoDao videoDao;

    //初始化数据库
    public void init(Application application) {

        tikTokDataBase = Room.databaseBuilder(application, TikTokDataBase.class, "tiktok.db")
                .allowMainThreadQueries()
                .build();

    }

    public VideoDao getVideoDao() {

        if (videoDao == null) {

            videoDao = tikTokDataBase.getVideoDao();

        }
        return videoDao;
    }


}
