package com.kf.waqtiktok.mvvm.pay;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.kf.waqtiktok.R;
import com.kf.waqtiktok.databinding.ActivityPayBinding;
import com.kf.waqtiktok.mvvm.base.BaseActivity;

/**
 * 作者： 王安卿
 * 日期： 2024/9/1 20:07
 * 功能： 调用支付宝充值
 *
 */

public class PayActivity extends BaseActivity<ActivityPayBinding,PayViewModel>{



    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected Class<PayViewModel> getViewModel() {
        return PayViewModel.class;
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_pay;
    }
}