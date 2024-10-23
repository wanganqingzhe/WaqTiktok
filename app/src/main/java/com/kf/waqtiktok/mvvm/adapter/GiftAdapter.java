package com.kf.waqtiktok.mvvm.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.kf.waqtiktok.R;
import com.kf.waqtiktok.mvvm.entity.GiftEntity;

import java.util.List;

public class GiftAdapter extends BaseQuickAdapter<GiftEntity.DataDTO, BaseViewHolder> {


    public GiftAdapter(int layoutResId, @Nullable List<GiftEntity.DataDTO> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, GiftEntity.DataDTO dataDTO) {

        //显示礼物图片
        Glide.with(getContext()).load(dataDTO.getGiftpath()).into((ImageView) holder.findView(R.id.iv_gift));
        //显示礼物价格
        holder.setText(R.id.tv_gift_money,dataDTO.getPrice()+"k");





    }
}
