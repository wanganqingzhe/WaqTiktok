package com.kf.waqtiktok.utils;

import com.kf.waqtiktok.mvvm.entity.CommentEntity;
import com.kf.waqtiktok.mvvm.entity.ConcernEntity;
import com.kf.waqtiktok.mvvm.entity.GiftEntity;
import com.kf.waqtiktok.mvvm.entity.UserEntity;
import com.kf.waqtiktok.mvvm.entity.VideoEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    //注册接口
    @POST("/user/register")
    Observable<UserEntity> enroll(@Body Map map);

    //登录接口
    @POST("/user/loginjson")
    Observable<UserEntity> login (@Body Map map);

    //短视频接口
    @GET("/video/findVideos")
    Observable<VideoEntity> video(@Query("currentPage") Integer currentPage,@Query("pageSize") Integer pageSize);


    /**
     *  关注
     * @return
     */
    @POST("/guanzhu/guanzhu")
    Observable<ConcernEntity> concernYes(@Body Map map);


    /**
     * 取消关注
     * @param map
     * @return
     */
   @POST("/guanzhu/noguanzhu")
    Observable<ConcernEntity> concernNo(@Body Map map);


    /**
     * 点赞
     * @param videoId
     * @return
     */
   @POST("/fav/love")
    Observable<ConcernEntity> love(@Query("videoId") Integer videoId);




    /**
     * 取消点赞
     * @param videoId
     * @return
     */
    @POST("/fav/nolove")
    Observable<ConcernEntity> noLove(@Query("videoId") Integer videoId);


    /**
     * 评论
     * @param videoId
     * @return
     */

     @GET("/comment/getCommentByVideoId")
    Observable<CommentEntity> comment(@Query("videoId") Integer videoId);

    /**
     * 发送评论
     *
     *
     */
   @POST("/comment/comment")
    Observable<CommentEntity> sendCommend(@Body Map map);

    /**
     * 展示礼物
     */
   @GET("/gift/json")
    Observable<GiftEntity> gift();























}
