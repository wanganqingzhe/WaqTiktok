package com.kf.waqtiktok.mvvm.live;

import androidx.lifecycle.MutableLiveData;

import com.kf.waqtiktok.mvvm.base.BaseViewModel;
import com.kf.waqtiktok.mvvm.entity.ConcernEntity;
import com.kf.waqtiktok.mvvm.entity.GiftEntity;

public class LiveViewModel extends BaseViewModel<LiveRepo> {
    @Override
    public LiveRepo createRepo() {
        return new LiveRepo();
    }

    /**
     * 关注
     */

    MutableLiveData<ConcernEntity> concernSuccess=new MutableLiveData<>();
    MutableLiveData<String> concernFail=new MutableLiveData<>();

    public void concern(String authname) {

        repo.concern(authname,concernSuccess,concernFail);

    }

    /**
     * 取消关注
      */
    MutableLiveData<ConcernEntity> noConcernSuccess=new MutableLiveData<>();
    MutableLiveData<String> noConcernFail=new MutableLiveData<>();

    public void noConcern(String authname) {

        repo.noConcern(authname,noConcernSuccess,noConcernFail);

    }

    //展示礼物


   public  MutableLiveData<GiftEntity> giftSuccess=new MutableLiveData<>();
   public  MutableLiveData<String> giftFail=new MutableLiveData<>();
    public void gift() {

           repo.gift(giftSuccess,giftFail);


    }
}
