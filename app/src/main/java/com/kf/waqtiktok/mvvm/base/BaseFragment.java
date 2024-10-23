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
 * 功能： 封装 fragment 有网
 * 使用抽象类
 */

public abstract class BaseFragment<VDB extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {


    protected VDB binding;
    protected VM viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //实例化Binding
       binding=DataBindingUtil.inflate(inflater,bindLayout(),container,false);
        //实例化 ViewBinding
        viewModel=new ViewModelProvider(this).get(getViewModel());


        initView();
        initData();


        return binding.getRoot();
    }



    //初始化data
    protected abstract void initData();


    //初始化 view
    protected abstract void initView();

    //获取 viewModel对象
    protected abstract Class<VM> getViewModel();

    //绑定数据
    protected abstract int bindLayout();


}
