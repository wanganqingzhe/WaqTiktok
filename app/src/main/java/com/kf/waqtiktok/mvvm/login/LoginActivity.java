package com.kf.waqtiktok.mvvm.login;

import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.method.Touch;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.exoplayer2.C;
import com.kf.waqtiktok.MainActivity;
import com.kf.waqtiktok.R;
import com.kf.waqtiktok.databinding.ActivityLoginBinding;
import com.kf.waqtiktok.mvvm.base.BaseActivity;
import com.kf.waqtiktok.mvvm.enroll.EnrollActivity;
import com.kf.waqtiktok.mvvm.entity.UserEntity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding,LoginViewModel> {




    @Override
    protected void initData() {

        //初始化数据
        //处理登录成功和失败
        viewModel.loginSuccess.observe(this, new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {

                ToastUtils.showShort(userEntity.getMessage());

                //登录成功
                if (userEntity.getCode()==200){

                    SPUtils.getInstance().put("token",userEntity.getData().getToken());
                    SPUtils.getInstance().put("username",userEntity.getData().getUsername());
                    SPUtils.getInstance().put("icon",userEntity.getData().getIcon());


                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                }

                //失败

                 viewModel.loginFail.observe(LoginActivity.this, new Observer<String>() {
                     @Override
                     public void onChanged(String s) {

                         ToastUtils.showShort(s);
                     }
                 });




            }
        });







    }

    @Override
    protected void initView() {


/*
        binding.eye.setOnClickListener(view ->
        {

            if (binding.loginPassword.getInputType()==129){
                binding.loginPassword.setInputType(128);
                binding.eye.setImageResource(R.drawable.yes);
            }else{
                binding.loginPassword.setInputType(129);
                binding.eye.setImageResource(R.drawable.no);

            }

        });
*/



        //点击跳转到注册页面
        binding.loginEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginActivity.this, EnrollActivity.class));


            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                viewModel.login(binding.loginName.getText().toString(),
                        binding.loginPassword.getText().toString());


            }
        });

       //将chockBox设置到富文本上
      String string= binding.cb.getText().toString();
      SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
      //用户协议设置点击事件
      ClickableSpan clickableSpan=new ClickableSpan() {
          @Override
          public void onClick(@NonNull View view) {


              startActivity(new Intent(LoginActivity.this, InformationActivity.class));
              ToastUtils.showShort("用户协议");

          }
      };
      //隐私协议设置点击事件
     ClickableSpan clickableSpan1=new ClickableSpan() {
         @Override
         public void onClick(@NonNull View view) {

             startActivity(new Intent(LoginActivity.this, InformationActivity.class));

             ToastUtils.showShort("隐私协议");

         }
     };
      spannableStringBuilder.setSpan(clickableSpan,7,13,Spanned.SPAN_INTERMEDIATE);
      spannableStringBuilder.setSpan(clickableSpan1,14,20,Spanned.SPAN_INTERMEDIATE);

      //设置颜色
      spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.BLUE),7,13,Spanned.SPAN_INTERMEDIATE);
      spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.BLUE),14,20,Spanned.SPAN_INTERMEDIATE);


       binding.cb.setMovementMethod(LinkMovementMethod.getInstance());
       binding.cb.setText(spannableStringBuilder);



        //密码显示和隐藏
        binding.eye.setOnClickListener(view -> {


             if (binding.loginPassword.getInputType()==129){

                 binding.loginPassword.setInputType(128);
                 binding.eye.setImageResource(R.drawable.yes);

             }else{

                 binding.loginPassword.setInputType(129);
                 binding.eye.setImageResource(R.drawable.no);

             }


        });





    }

    @Override
    protected Class<LoginViewModel> getViewModel() {
        return LoginViewModel.class;
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_login;
    }
}