package com.kf.waqtiktok.mvvm.enroll;

import androidx.lifecycle.MutableLiveData;

import com.kf.waqtiktok.mvvm.base.BaseRepo;
import com.kf.waqtiktok.mvvm.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EnrollRepo extends BaseRepo {


    public void enroll(String username, String password, String repassword, MutableLiveData<UserEntity> enrollSuccess, MutableLiveData<String> enrollFail) {

        Map map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        map.put("repassword", repassword);

        apiService.enroll(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserEntity userEntity) {

                        //成功时调用
                        enrollSuccess.postValue(userEntity);
                    }

                    @Override
                    public void onError(Throwable e) {

                        //失败是调用
                        enrollFail.postValue(e.getMessage());


                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


}
