package com.kf.waqtiktok.mvvm.adapter;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.kf.waqtiktok.R;
import com.kf.waqtiktok.mvvm.entity.MessageEntity;

import java.util.List;

import retrofit2.http.GET;

/**
 * 直播间消息适配器
 *
 */

public class MessageAdapter extends BaseQuickAdapter<MessageEntity, BaseViewHolder> {


    public MessageAdapter(int layoutResId, @Nullable List<MessageEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder holder, MessageEntity messageEntity) {

          //头像
        Glide.with(getContext()).load(messageEntity.getIcon()).circleCrop().into((ImageView) holder.getView(R.id.iv_tou));
        //用户名和消息 显示
         String text= messageEntity.getUsername()+":"+messageEntity.getMsg();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.BLUE),0,messageEntity.getUsername().length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        holder.setText(R.id.tv_text,spannableStringBuilder);


    }
}
