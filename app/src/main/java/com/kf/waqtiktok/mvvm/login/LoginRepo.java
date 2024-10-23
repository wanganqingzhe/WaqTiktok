package com.kf.waqtiktok.mvvm.login;

import androidx.lifecycle.MutableLiveData;

import com.kf.waqtiktok.mvvm.base.BaseRepo;
import com.kf.waqtiktok.mvvm.entity.UserEntity;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginRepo extends BaseRepo {


    public void login(String username, String password, MutableLiveData<UserEntity> loginSuccess, MutableLiveData<String> loginFail) {

        Map map=new HashMap();
        map.put("username",username);
        map.put("password",password);
        apiService.login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserEntity>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserEntity userEntity) {

                        //成功时调用
                        loginSuccess.postValue(userEntity);



                    }

                    @Override
                    public void onError(Throwable e) {

                        //失败时调用
                       loginFail.postValue(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

}
