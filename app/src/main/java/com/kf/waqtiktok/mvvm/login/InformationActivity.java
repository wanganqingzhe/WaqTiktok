package com.kf.waqtiktok.mvvm.login;

import android.os.Bundle;
import android.webkit.WebChromeClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.kf.waqtiktok.R;
import com.kf.waqtiktok.databinding.ActivityInformationBinding;
import com.kf.waqtiktok.mvvm.base.BaseViewActivity;

public class InformationActivity extends BaseViewActivity<ActivityInformationBinding> {



    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

         binding.web.loadUrl("");
         binding.web.setWebChromeClient(new WebChromeClient());

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_information;
    }
}