package com.kf.waqtiktok.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


/**
 * 作者： 王安卿
 * 日期： 2024/8/24 11:49
 * 功能：
 */
public class DiyRecycleView extends RecyclerView {

    // 是否拦截事件，默认为 true表示拦截
    private boolean isIntercept = true;

    public void setIntercept(boolean intercept) {
        isIntercept = intercept;
    }

    public DiyRecycleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (isIntercept) {
            return super.onInterceptTouchEvent(e);
        } else {
            return false;
        }

    }
}
