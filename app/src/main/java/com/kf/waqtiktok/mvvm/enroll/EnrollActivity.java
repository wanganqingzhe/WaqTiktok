package com.kf.waqtiktok.mvvm.enroll;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.blankj.utilcode.util.ToastUtils;
import com.kf.waqtiktok.MainActivity;
import com.kf.waqtiktok.R;
import com.kf.waqtiktok.databinding.ActivityEnrollBinding;
import com.kf.waqtiktok.mvvm.base.BaseActivity;

import java.util.Random;

public class EnrollActivity extends BaseActivity<ActivityEnrollBinding, EnrollViewModel> {


    @Override
    protected void initData() {


        //注册成功
        viewModel.enrollSuccess.observe(this, userEntity -> {

            ToastUtils.showShort(userEntity.getMessage());
            if (userEntity.getCode() == 200) {

                startActivity(new Intent(EnrollActivity.this, MainActivity.class));
                finish();

            }

            //失败
            //    viewModel.enrollFail.observe(EnrollActivity.this, (Observer<String>) s -> ToastUtils.showShort(s));

            viewModel.enrollFail.observe(EnrollActivity.this, new Observer<String>() {
                @Override
                public void onChanged(String s) {

                    Toast.makeText(EnrollActivity.this, "注册失败" + s, Toast.LENGTH_SHORT).show();

                }
            });


        });


    }

    private  int code;

    @Override
    protected void initView() {


        //点击获取验证码
        binding.enrollGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               code=new Random().nextInt(999999);
               ToastUtils.showShort("您的验证码是"+code);

               //开始倒计时 让注册按钮不可用  60s后可用
                binding.enrollGetCode.setEnabled(false);
                new CountDownTimer(60000,1000){


                    @Override
                    public void onTick(long l) {

                        binding.enrollGetCode.setText(l/1000+"后再获取");
                    }

                    @Override
                    public void onFinish() {

                        binding.enrollGetCode.setEnabled(true);
                        binding.enrollGetCode.setText("获取验证码");

                    }
                }.start();




            }
        });

        //判断验证码格式是否正确
        binding.enrollCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length()==6){

                    binding.enroll.setEnabled(true);
                }else {
                    binding.enroll.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




        //点击返回
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //点击注册按钮
        binding.enroll.setOnClickListener(view -> {



            //判断两次  密码是否一致
            if (!binding.eneollPassword.getText().toString().equals(binding.enrollOkPassword.getText().toString())) {

                Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                return;

            }

            //判断验证码是否与获取的一致
            if (code!=Integer.parseInt(binding.enrollCode.getText().toString())){
                Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
            }



            viewModel.enroll(binding.enrollName.getText().toString(),
                    binding.eneollPassword.getText().toString(),
                    binding.enrollOkPassword.getText().toString());


        });


    }

    @Override
    protected Class<EnrollViewModel> getViewModel() {
        return EnrollViewModel.class;
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_enroll;
    }
}