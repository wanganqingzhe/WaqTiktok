package com.kf.waqtiktok.mvvm.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

/**
 * 作者： 王安卿
 * 日期： 2024/8/18 20:32
 * 功能： 封装 fragment 无网
 * 使用抽象类
 */

public abstract class BaseViewFragment<VDB extends ViewDataBinding> extends Fragment {


    protected VDB binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //实例化Binding
        binding = DataBindingUtil.inflate(inflater, bindLayout(), container, false);


        initView();
        initData();


        return binding.getRoot();
    }


    //初始化data
    protected abstract void initData();


    //初始化 view
    protected abstract void initView();

    //绑定数据
    protected abstract int bindLayout();


}
