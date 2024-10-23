package com.kf.waqtiktok.mvvm.live;

import static java.lang.System.exit;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.kf.waqtiktok.R;
import com.kf.waqtiktok.databinding.ActivityLiveBinding;
import com.kf.waqtiktok.mvvm.adapter.GiftAdapter;
import com.kf.waqtiktok.mvvm.adapter.MessageAdapter;
import com.kf.waqtiktok.mvvm.base.BaseActivity;
import com.kf.waqtiktok.mvvm.entity.ConcernEntity;
import com.kf.waqtiktok.mvvm.entity.GiftEntity;
import com.kf.waqtiktok.mvvm.entity.MessageEntity;
import com.kf.waqtiktok.mvvm.entity.VideoEntity;
import com.kf.waqtiktok.mvvm.pay.PayActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LiveActivity extends BaseActivity<ActivityLiveBinding,LiveViewModel> {


    //直播视频数据
    private   VideoEntity.DataDTO bean;

     //直播见消息
    private List<MessageEntity> messageList=new ArrayList<>();
    private MessageAdapter  messageAdapter=new MessageAdapter(R.layout.item_message,messageList);


    //送礼物数据 与适配器
     private  List<GiftEntity.DataDTO> giftList=new ArrayList<>();
     private GiftAdapter giftAdapter=new GiftAdapter(R.layout.item_gift,giftList);
     int gifIndex=1;











    @Override
    protected void initData() {

        //关注成功
        viewModel.concernSuccess.observe(this, concernEntity -> {

            ToastUtils.showShort(concernEntity.getMessage());

            if (concernEntity.getCode()==200){

                //关注成功后修改为粉丝
                binding.tvGuanzhu.setText("粉丝");
                //修改背景颜色为灰色
                binding.tvGuanzhu.setBackgroundResource(R.drawable.tv_input);

                //修改关注的状态
                bean.setGuanzhu(1);


            }

        });

        //取消关注成功
        viewModel.noConcernSuccess.observe(this, new Observer<ConcernEntity>() {
            @Override
            public void onChanged(ConcernEntity concernEntity) {

                if (concernEntity.getCode()==200){

                    binding.tvGuanzhu.setText("关注");
                    binding.tvGuanzhu.setBackgroundResource(R.drawable.tv_input);
                     bean.setGuanzhu(0);



                }

            }
        });
        viewModel.giftSuccess.observe(this, new Observer<GiftEntity>() {
            @Override
            public void onChanged(GiftEntity giftEntity) {

                if (giftEntity.getCode()==200){

                    giftList.addAll(giftEntity.getData());
                    giftAdapter.notifyDataSetChanged();


                }


            }
        });



    }

    @Override
    protected void initView() {

         bean = (VideoEntity.DataDTO) getIntent().getSerializableExtra("data");
         //初始化播放器
        initGsy();
        //初始化用户信息
        initUser();
        //点赞
        love();
        //初始化系统消息
        initSystem();

        //退出直播间
        exit();
        //关注博主
        concern();
        //发消息
        message();
        //展示礼物
        gift();
        //送礼
        sendGift();


    }

    /**
     * 送礼
     */

    private void sendGift() {

      //发送礼物按钮点击
        binding.btnFasong.setOnClickListener(view -> {


            //校验是否满足送礼物的条件
            if (gifIndex<0){

                ToastUtils.showShort("请选择礼物");
                return;

            }
            //判断余额是否大于礼物花费
             int money=  SPUtils.getInstance().getInt("money",0);
            Integer price=giftList.get(gifIndex).getPrice();
            if (money<price){

                ToastUtils.showShort("余额不足,请充值");
                return;

            }
            //扣钱
            money-=price;
            SPUtils.getInstance().put("money",money);//修改钱包余额
            binding.tvDoubi.setText(money+"元");  //修改页面显余额

            binding.ivGiftShow.setVisibility(View.VISIBLE);
            //修改送出礼物动画的图片
            Glide.with(LiveActivity.this).load(giftList.get(gifIndex).getGiftpath()).into(binding.ivGiftShow);
            //播放动画
            //1平移
            int width=getWindowManager().getDefaultDisplay().getWidth();
            int height=getWindowManager().getDefaultDisplay().getHeight();
            //设置动画
            ObjectAnimator animatorX=ObjectAnimator.ofFloat(binding.ivGiftShow,"translationX",0,width,width * 2/ 5,width /2-100).setDuration(2000);
            ObjectAnimator animatorY=ObjectAnimator.ofFloat(binding.ivGiftShow,"translationY",0,height/5,height * 2 /5,height / 2-100).setDuration(2000);
            //2.旋转
            ObjectAnimator animator=ObjectAnimator.ofFloat(binding.ivGiftShow,"rotation",0,360);
            animator.setDuration(1000);
            animator.setInterpolator(new LinearInterpolator());
            //3.缩放
            ObjectAnimator scaleX=ObjectAnimator.ofFloat(binding.ivGiftShow,"scaleX",1,2,1).setDuration(1000);
            ObjectAnimator scaleY=ObjectAnimator.ofFloat(binding.ivGiftShow,"scaleY",1,2,1).setDuration(1000);
            //动画集合
            AnimatorSet set = new AnimatorSet();
            set.play(animatorX).with(animatorY).before(animator).before(scaleX).before(scaleY);
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationCancel(Animator animation) {

                    //隐藏送礼图片
                    binding.ivGiftShow.setVisibility(View.GONE);


                }
            });
          set.start();

        });



    }

    /**
     * 初始化系统消息
     */

    private void initSystem() {

         String string= binding.tvHuanying.getText().toString();
         string=string.replace("XXX",SPUtils.getInstance().getString("username"));
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.BLUE),0,3, Spanned.SPAN_INTERMEDIATE);
        binding.tvHuanying.setText(spannableStringBuilder);


    }

    /**
     * 展示礼物
     */

    private void gift() {



        // 礼物RecycleView 绑定适配器
        binding.recGift.setLayoutManager(new GridLayoutManager(this, 4));
        binding.recGift.setAdapter(giftAdapter);

        // 实现礼物选中标识
        giftAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {

                // 底部选中礼物标识，显示点击的礼物
                Glide.with(LiveActivity.this).load(giftList.get(position).getGiftpath()).into(binding.ivLiwuchecked);
                // 修改选中礼物标识
                gifIndex = position;
            }
        });

        binding.ivLiwu.setOnClickListener(v -> {
            // 显示礼物
            binding.llLiwu.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(LiveActivity.this, R.anim.in);
            animation.setInterpolator(new BounceInterpolator());
            binding.llLiwu.startAnimation(animation);
        });
        // 关闭礼物
        binding.ivLiwuguanbi.setOnClickListener(v -> {
            binding.llLiwu.setVisibility(View.GONE);
        });
        // 跳转到充值页面
        binding.tvPay.setOnClickListener(v -> {
            startActivity(new Intent(LiveActivity.this, PayActivity.class));
        });
        viewModel.gift();



    }


    /**
     * 发送消息
      */
    private void message() {

        // 消息列表绑定适配器
        binding.recMessage.setLayoutManager(new LinearLayoutManager(this));
        binding.recMessage.setAdapter(messageAdapter);
        // 初始化一些假消息
        initMessage();
        // 显示消息输入框
        binding.tvInput.setOnClickListener(v -> {
            // 显示消息输入框
            binding.llMessage.setVisibility(View.VISIBLE);
            Animation animation = AnimationUtils.loadAnimation(LiveActivity.this, R.anim.in);
            binding.llMessage.startAnimation(animation);
        });
        // 发送图标点击
        binding.ivFasong.setOnClickListener(v -> {
            // 1. 获取输入框中的内容
            String msg = binding.etInput.getText().toString();
            // 2. 把输入框清空
            binding.etInput.getText().clear();
            // 3. 隐藏消息输入框
            binding.llMessage.setVisibility(View.GONE);
            // 4. 把输入框中的消息显示到消息聊天室
            messageList.add(
                    new MessageEntity(
                            SPUtils.getInstance().getString("icon"),
                            SPUtils.getInstance().getString("username"),
                            msg
                    )
            );
            messageAdapter.notifyDataSetChanged();
            // 让消息列表滚动到最后一条
            binding.recMessage.scrollToPosition(messageList.size()-1);


        });



    }

    private void initMessage() {
        messageList.add(new MessageEntity(
                "https://img1.baidu.com/it/u=722872937,1945399494&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500",
                "孙悟空",
                "大家好！"
        ));
        messageList.add(new MessageEntity(
                "https://ww3.sinaimg.cn/mw690/007rSltply1hrp5vqqaj5j30zu0zugsx.jpg",
                "猪八戒",
                "大家好！"
        ));
        messageList.add(new MessageEntity(
                "https://wx1.sinaimg.cn/mw690/ecf974bbgy1hs844z5es8j21hc1hc13c.jpg",
                "沙僧",
                "大家好！"
        ));
        messageList.add(new MessageEntity(
                "https://img1.baidu.com/it/u=623918047,3044474100&fm=253&app=138&f=JPEG?w=495&h=495",
                "唐僧",
                "大家好！"
        ));
        messageList.add(new MessageEntity(
                "https://img1.baidu.com/it/u=722872937,1945399494&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500",
                "小白龙",
                "大家好！"
        ));
        messageList.add(new MessageEntity(
                "https://img1.baidu.com/it/u=722872937,1945399494&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500",
                "孙悟空",
                "大家好！"
        ));
        messageList.add(new MessageEntity(
                "https://ww3.sinaimg.cn/mw690/007rSltply1hrp5vqqaj5j30zu0zugsx.jpg",
                "猪八戒",
                "大家好！"
        ));
        messageList.add(new MessageEntity(
                "https://wx1.sinaimg.cn/mw690/ecf974bbgy1hs844z5es8j21hc1hc13c.jpg",
                "沙僧",
                "大家好！"
        ));
        messageList.add(new MessageEntity(
                "https://img1.baidu.com/it/u=623918047,3044474100&fm=253&app=138&f=JPEG?w=495&h=495",
                "唐僧",
                "大家好！"
        ));
        messageList.add(new MessageEntity(
                "https://img1.baidu.com/it/u=722872937,1945399494&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500",
                "小白龙",
                "大家好！"
        ));
    }

    /**
     *
     * 关注
     *
     */
    private void concern() {

        binding.tvGuanzhu.setOnClickListener(view -> {

            if (bean.getGuanzhu()==1){  //取消关注

                viewModel.noConcern(bean.getAuthname());


            }else{  //关注

                viewModel.concern(bean.getAuthname());



            }



        });


    }


    /**
     * 退出直播间
     *
     */

    private void exit() {

       binding.ivClose.setOnClickListener(view -> finish());

    }



    /**
     * 点赞
     */

    private void love() {

        binding.ivDianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                binding.ksgLikeView.addLikeImages(R.drawable.ic_xihuanred,R.drawable.ic_xihuan);
                binding.ksgLikeView.addFavor();

            }
        });


    }

    /*
       播放视频
     */

    private void initGsy() {

        binding.gsy.setUp(bean.getVideopath(),true,"");
        binding.gsy.getBackButton().setVisibility(View.GONE);
        binding.gsy.getBackButton().setVisibility(View.GONE);
        binding.gsy.startPlayLogic();


    }

    /**
     * 初始化用户信息
     * @return
     */

    private void initUser() {

        //显示圆形头像
        Glide.with(this).load(bean.getHeadpath()).circleCrop().into(binding.ivTou);
        binding.tvAuthor.setText(bean.getAuthname());
        binding.tvDianzan.setText(bean.getLike_count()+"本场点赞");
        //显示关注状态
        if (bean.getGuanzhu()==1){  //已关注
            binding.tvGuanzhu.setText("粉丝");
            binding.tvGuanzhu.setBackgroundResource(R.drawable.tv_input);

        }else{
            binding.tvGuanzhu.setText("关注");
            binding.tvGuanzhu.setBackgroundResource(R.drawable.tv_input);

        }





    }


    @Override
    protected Class<LiveViewModel> getViewModel() {
        return  LiveViewModel.class;
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_live;
    }
}