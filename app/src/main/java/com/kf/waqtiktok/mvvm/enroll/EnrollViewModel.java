package com.kf.waqtiktok.mvvm.enroll;

import androidx.lifecycle.MutableLiveData;

import com.kf.waqtiktok.mvvm.base.BaseViewModel;
import com.kf.waqtiktok.mvvm.entity.UserEntity;

public class EnrollViewModel extends BaseViewModel<EnrollRepo> {


    @Override
    public EnrollRepo createRepo() {
        return new EnrollRepo();
    }


    //处理成功
    public MutableLiveData<UserEntity> enrollSuccess = new MutableLiveData<>();
    //处理失败
    public MutableLiveData<String> enrollFail = new MutableLiveData<>();


    public void enroll(String username, String password, String repassword) {


        repo.enroll(username, password, repassword, enrollSuccess, enrollFail);

    }



}
