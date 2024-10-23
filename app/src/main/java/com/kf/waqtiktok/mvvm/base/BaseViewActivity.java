package com.kf.waqtiktok.mvvm.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

/**
 * 作者： 王安卿
 * 日期： 2024/8/18 20:23
 * 功能：  封装无网的activity
 * 抽象类
 */

public abstract class  BaseViewActivity<VDB extends ViewDataBinding> extends AppCompatActivity {

    protected VDB binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //实例化Binding
        binding = DataBindingUtil.setContentView(this, bindLayout());

        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //防止内存泄露
        binding = null;

    }

    //初始化数据
    protected abstract void initData();

    //初始化 view
    protected abstract void initView();

    //绑定布局
    protected abstract int bindLayout();


}
