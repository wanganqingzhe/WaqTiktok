package com.kf.waqtiktok.utils.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.kf.waqtiktok.mvvm.entity.VideoEntity;

import java.util.List;

@Dao
public interface VideoDao {

    /**
     * 向数据库中添加数据
     */
    @Insert
    void insertVideo(VideoEntity.DataDTO dataDTO);

    /**
     * 从数据库中 删除数据
     */

     @Delete
    void deleteVideo(VideoEntity.DataDTO dataDTO);

    /**
     * 查询全部
     */
    @Query("select * from video")
    List<VideoEntity.DataDTO> queryAll();



    /**
     * 根据id 查询
     */
    @Query("select * from video where id=:id")
    VideoEntity.DataDTO queryById(int id);





}
