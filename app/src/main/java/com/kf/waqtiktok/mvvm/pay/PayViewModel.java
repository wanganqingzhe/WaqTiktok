package com.kf.waqtiktok.mvvm.pay;

import com.kf.waqtiktok.mvvm.base.BaseViewModel;

public class PayViewModel extends BaseViewModel<PayRepo> {
    @Override
    public PayRepo createRepo() {
        return new PayRepo();
    }
}
