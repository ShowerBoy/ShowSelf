package com.showself.showself;

import com.fly.tour.api.RetrofitManager;
import com.fly.tour.common.BaseApplication;

public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitManager.init(this);
    }
}
