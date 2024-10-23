package com.kf.waqtiktok.mvvm.login;

import androidx.lifecycle.MutableLiveData;

import com.kf.waqtiktok.mvvm.base.BaseViewModel;
import com.kf.waqtiktok.mvvm.entity.UserEntity;

public class LoginViewModel extends BaseViewModel<LoginRepo> {
    @Override
    public LoginRepo createRepo() {
        return new LoginRepo();
    }

    /*//处理成功
    public MutableLiveData<UserEntity> loginSuccess = new MutableLiveData<>();
    //处理失败
    public MutableLiveData<String> loginFail = new MutableLiveData<>();*/



    //处理登录成功
    public  MutableLiveData<UserEntity> loginSuccess=new MutableLiveData<>();

    //处理登录失败
    public  MutableLiveData<String> loginFail=new MutableLiveData<>();



    public void login(String username, String password) {


        repo.login(username,password,loginSuccess,loginFail);
    }
}
