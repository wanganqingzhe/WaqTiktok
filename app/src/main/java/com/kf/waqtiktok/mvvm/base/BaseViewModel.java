package com.kf.waqtiktok.mvvm.base;

import androidx.lifecycle.ViewModel;

/**
 * 作者： 王安卿
 * 日期： 2024/8/18 20:19
 * 功能： 封装  viewModel 数据和 view 的粘合剂
 *    使用抽象类
 *
 */

public   abstract class BaseViewModel<Repo extends BaseRepo> extends ViewModel  {



    protected  Repo repo;

    public BaseViewModel() {

        repo=createRepo();

    }
    public  abstract  Repo createRepo();

}
