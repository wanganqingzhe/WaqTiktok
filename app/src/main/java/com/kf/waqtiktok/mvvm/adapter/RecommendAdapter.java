package com.kf.waqtiktok.mvvm.adapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.kf.waqtiktok.R;
import com.kf.waqtiktok.mvvm.entity.VideoEntity;
import com.kf.waqtiktok.utils.db.RoomDbUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.List;

public class RecommendAdapter extends BaseMultiItemQuickAdapter<VideoEntity.DataDTO, BaseViewHolder> {


    public  RecommendAdapter(@NonNull List<VideoEntity.DataDTO> data){

        super(data);
        //根据  videoEntity.DataBean 的type 匹配不同的条目布局
        addItemType(0, R.layout.item_video_recommend);
        addItemType(1,R.layout.item_video_live);
        addItemType(2,R.layout.item_video_full);



    }



    @Override
    protected void convert(@NonNull BaseViewHolder holder, VideoEntity.DataDTO dataDTO) {

        //视频播放
        //找到播放器
        StandardGSYVideoPlayer videoPlayer=holder.findView(R.id.gsy);
        //设置视频地址
        videoPlayer.setUp(dataDTO.getVideopath(),true,"");
        //播放视频
        videoPlayer.startPlayLogic();

        //跑马灯
        //1.设置跑马灯的文字
        holder.setText(R.id.tv_pao,dataDTO.getAuthname()+"创作的原创音乐");
        //让当前条目的跑马灯再次请求一下焦点
        TextView textview = holder.findView(R.id.tv_pao);
        textview.requestFocus();

        //其他文字
        holder.setText(R.id.tv_caption,dataDTO.getCaption()); //简介
        holder.setText(R.id.tv_author,dataDTO.getAuthname());//作者
        holder.setText(R.id.tv_dingwei,dataDTO.getAddress());

        //特殊情况,三种布局控件不同 ,直播没有右边操作栏 判断是不是直播布局
        switch (dataDTO.getItemType()){

            case 0: //普通
            case 2:  //全屏


                ImageView concern = holder.findView(R.id.iv_guanzhu);
                if (dataDTO.getGuanzhu()==1){

                    concern.setVisibility(View.GONE);

                }else{

                    concern.setVisibility(View.VISIBLE);

                }
               //显示点赞 - 点赞过红心 没点赞过 灰色
                ImageView love = holder.findView(R.id.iv_dianzan);
                if (dataDTO.getDianzan()==1){

                    love.setImageResource(R.drawable.ic_xihuanred);

                }else {
                      love.setImageResource(R.drawable.ic_xihuan);
                }


                holder.setText(R.id.tv_dianzan,dataDTO.getLike_count()+"");  //点赞数量
                //显示头像
                ImageView ivTou=holder.findView(R.id.iv_tou);
                if(ivTou != null) {
                    Glide.with(getContext()).load(dataDTO.getHeadpath()).circleCrop().into(ivTou);
                }
                //显示转
                ImageView ivZhuan = holder.findView(R.id.iv_zhuan);
                if (ivZhuan != null) {
                    Glide.with(getContext()).load(dataDTO.getHeadpath()).circleCrop().into(ivZhuan);
                    //使用属性 动画  让转无限旋转
                    ObjectAnimator animator = ObjectAnimator.ofFloat(ivZhuan, "rotation", 0, 360);
                    animator.setDuration(2000);//时长
                    animator.setInterpolator(new LinearInterpolator());//均速
                    animator.setRepeatCount(ValueAnimator.INFINITE); //无线旋转
                    animator.start();
                }

             //显示是否收藏
              ImageView ivLike=  holder.findView(R.id.iv_shoucang);
             VideoEntity.DataDTO queriedById=   RoomDbUtils.getInstance().getVideoDao().queryById(dataDTO.getId());
                 if (queriedById==null){
                     ivLike.setImageResource(R.drawable.ic_shoucang);
                 }else{

                     ivLike.setImageResource(R.drawable.ic_shoucangyellow);


                 }


                break;
            case 1:

                break;



        }









    }
}
