package com.kf.waqtiktok.mvvm.recommend;

import androidx.lifecycle.MutableLiveData;

import com.kf.waqtiktok.mvvm.base.BaseViewModel;
import com.kf.waqtiktok.mvvm.entity.CommentEntity;
import com.kf.waqtiktok.mvvm.entity.ConcernEntity;
import com.kf.waqtiktok.mvvm.entity.VideoEntity;

public class RecommendViewModel extends BaseViewModel<RecommendRepo> {
    @Override
    public RecommendRepo createRepo() {
        return new RecommendRepo();
    }

    //处理成功
    MutableLiveData<VideoEntity> videoSuccess=new MutableLiveData<>();
    //处理失败
    MutableLiveData<String> videoFail=new MutableLiveData<>();


    public  void video(int currentPage,int pageSize){

        repo.video(currentPage,pageSize,videoSuccess,videoFail);

    }




    //关注成功
    public  MutableLiveData<ConcernEntity> concernSuccess=new MutableLiveData<>();
    //关注失败
    public  MutableLiveData<String> concernFail=new MutableLiveData<>();


    public void concern(String authname) {

      repo.concern(authname,concernSuccess,concernFail);


    }

    //点赞

    MutableLiveData<ConcernEntity> loveSuccess=new MutableLiveData<>();
    MutableLiveData<String> loveFail=new MutableLiveData<>();


    public void love(int videoId) {

         repo.love(videoId,loveSuccess,loveFail);

    }


    //取消点赞

    MutableLiveData<ConcernEntity> noLoveSuccess=new MutableLiveData<>();
    MutableLiveData<String> noLoveFail=new MutableLiveData<>();

    public void noLove(int videoId) {
       repo.noLove(videoId,noLoveSuccess,noLoveFail);

    }

 //评论
    MutableLiveData<CommentEntity> commentSuccess=new MutableLiveData<>();
    MutableLiveData<String> commentFail=new MutableLiveData<>();
    public void comment(int videoId) {
        repo.comment(videoId,commentSuccess,commentFail);

    }


    //发送评论
    public MutableLiveData<CommentEntity> sendSuccess=new MutableLiveData<>();
    public  MutableLiveData<String> sendFail=new MutableLiveData<>();
    public void sendCommend(int videoId, String msg) {

           repo.sendComment(videoId,msg,sendSuccess,sendFail);

    }
}
