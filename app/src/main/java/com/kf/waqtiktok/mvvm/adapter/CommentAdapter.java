package com.kf.waqtiktok.mvvm.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.kf.waqtiktok.R;
import com.kf.waqtiktok.mvvm.entity.CommentEntity;

import java.util.List;

public class CommentAdapter extends BaseQuickAdapter<CommentEntity.DataDTO, BaseViewHolder> {
    public CommentAdapter(int layoutResId, @Nullable List<CommentEntity.DataDTO> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, CommentEntity.DataDTO dataDTO) {

        //显示头像
        Glide.with(getContext()).load(dataDTO.getIcon()).circleCrop().into((ImageView) holder.getView(R.id.iv_tou));
        //显示评论用户名
        holder.setText(R.id.tv_username,dataDTO.getUsername());
        //显示评论内容
        holder.setText(R.id.tv_msg,dataDTO.getMsg());
        //显示评论时间
        holder.setText(R.id.tv_time,dataDTO.getCreatetime());









    }
}
