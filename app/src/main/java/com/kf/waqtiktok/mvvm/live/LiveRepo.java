package com.kf.waqtiktok.mvvm.live;

import androidx.lifecycle.MutableLiveData;

import com.kf.waqtiktok.mvvm.base.BaseRepo;
import com.kf.waqtiktok.mvvm.entity.ConcernEntity;
import com.kf.waqtiktok.mvvm.entity.GiftEntity;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LiveRepo extends BaseRepo {

    /**
     * 取消关注
     * @param authname
     * @param noConcernSuccess
     * @param noConcernFail
     */

    public void noConcern(String authname, MutableLiveData<ConcernEntity> noConcernSuccess, MutableLiveData<String> noConcernFail) {

      Map map=new HashMap();
      map.put("authname",authname);

      apiService.concernNo(map)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Observer<ConcernEntity>() {
                  @Override
                  public void onSubscribe(Disposable d) {

                  }

                  @Override
                  public void onNext(ConcernEntity concernEntity) {

                      noConcernSuccess.postValue(concernEntity);

                  }

                  @Override
                  public void onError(Throwable e) {

                       noConcernFail.postValue(e.getMessage());

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

        Map map=new HashMap();
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

    public void gift(MutableLiveData<GiftEntity> giftSuccess, MutableLiveData<String> giftFail) {

        apiService.gift()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GiftEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GiftEntity giftEntity) {

                        giftSuccess.postValue(giftEntity);
                    }

                    @Override
                    public void onError(Throwable e) {

                        giftFail.postValue(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
