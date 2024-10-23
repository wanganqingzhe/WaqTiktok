package com.kf.waqtiktok.mvvm.base;

import com.kf.waqtiktok.utils.ApiService;
import com.kf.waqtiktok.utils.HttpUtils;

/**
 * 作者： 王安卿
 * 日期： 2024/8/18 20:17
 * 功能： 封装repo 层 获取网络数据
 */

public class BaseRepo {

    protected ApiService apiService;

    public BaseRepo() {

        apiService = HttpUtils.getInstance().getRetrofit().create(ApiService.class);

    }
}
