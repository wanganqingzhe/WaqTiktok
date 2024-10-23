package com.kf.waqtiktok.utils.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.kf.waqtiktok.mvvm.entity.VideoEntity;

/**
 * 作者： 王安卿
 * 日期： 2024/8/24 12:14
 * 功能：  App 数据库
 */

@Database(entities ={VideoEntity.DataDTO.class},version  =1,exportSchema = false)
public abstract class TikTokDataBase extends RoomDatabase {


    abstract  public VideoDao  getVideoDao();

}
