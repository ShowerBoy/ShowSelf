package com.showself.module.my.mvvm.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fly.tour.common.mvvm.viewmodel.BaseViewModel;
import com.showself.module.my.mvvm.model.MyModel;

public class MyViewModel extends BaseViewModel<MyModel> {

    public MyViewModel(@NonNull Application application, MyModel model) {
        super(application, model);
    }
}
