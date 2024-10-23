package com.kf.waqtiktok.mvvm.recommend;

import androidx.lifecycle.MutableLiveData;

import com.kf.waqtiktok.mvvm.base.BaseRepo;
import com.kf.waqtiktok.mvvm.entity.CommentEntity;
import com.kf.waqtiktok.mvvm.entity.ConcernEntity;
import com.kf.waqtiktok.mvvm.entity.VideoEntity;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RecommendRepo extends BaseRepo {
    public void video(int currentPage, int pageSize, MutableLiveData<VideoEntity> videoSuccess, MutableLiveData<String> videoFail) {

        /**
         * 获取视频
         */
        apiService.video(currentPage,pageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VideoEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VideoEntity videoEntity) {
                       //处理成功
                       videoSuccess.postValue(videoEntity);


                    }

                    @Override
                    public void onError(Throwable e) {
                       //处理失败
                        videoFail.postValue(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    /**
     * 关注
     * @param authname
     * @param concernSuccess
     * @param concernFail
     */
    public void concern(String authname, MutableLiveData<ConcernEntity> concernSuccess, MutableLiveData<String> concernFail) {

        Map map= new HashMap();
        map.put("authname",authname);
        apiService.concernYes(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConcernEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {



                    }

                    @Override
                    public void onNext(ConcernEntity concernEntity) {

                        concernSuccess.postValue(concernEntity);

                    }

                    @Override
                    public void onError(Throwable e) {

                        concernFail.postValue(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });






    }

    /**
     * 点赞
     * @param videoId
     * @param loveSuccess
     * @param loveFail
     */
    public void love(int videoId, MutableLiveData<ConcernEntity> loveSuccess, MutableLiveData<String> loveFail) {

        apiService.love(videoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConcernEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ConcernEntity concernEntity) {

                        loveSuccess.postValue(concernEntity);

                    }

                    @Override
                    public void onError(Throwable e) {

                        loveFail.postValue(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void noLove(int videoId, MutableLiveData<ConcernEntity> noLoveSuccess, MutableLiveData<String> noLoveFail) {

        apiService.noLove(videoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConcernEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ConcernEntity concernEntity) {

                        noLoveSuccess.postValue(concernEntity);

                    }

                    @Override
                    public void onError(Throwable e) {
                     noLoveFail.postValue(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    /**
     * 显示评论
     * @param videoId
     * @param commentSuccess
     * @param commentFail
     */
    public void comment(int videoId, MutableLiveData<CommentEntity> commentSuccess, MutableLiveData<String> commentFail) {


          apiService.comment(videoId)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Observer<CommentEntity>() {
                      @Override
                      public void onSubscribe(Disposable d) {

                      }

                      @Override
                      public void onNext(CommentEntity commentEntity) {

                          commentSuccess.postValue(commentEntity);
                      }

                      @Override
                      public void onError(Throwable e) {

                          commentFail.postValue(e.getMessage());
                      }

                      @Override
                      public void onComplete() {

                      }
                  });



    }

    /**
     * 发送评论
     * @param videoId
     * @param msg
     * @param sendSuccess
     * @param sendFail
     */
    public void sendComment(int videoId, String msg, MutableLiveData<CommentEntity> sendSuccess, MutableLiveData<String> sendFail) {

        Map map=new HashMap();
        map.put("videoId",videoId);
        map.put("msg",msg);
        apiService.sendCommend(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CommentEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CommentEntity commentEntity) {

                        sendSuccess.postValue(commentEntity);
                    }

                    @Override
                    public void onError(Throwable e) {

                        sendFail.postValue(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
