package com.showself.module.discover.mvvm.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fly.tour.common.mvvm.viewmodel.BaseViewModel;
import com.showself.module.discover.mvvm.model.DiscoverModel;

public class DiscoverViewModel extends BaseViewModel<DiscoverModel> {

    public DiscoverViewModel(@NonNull Application application, DiscoverModel model) {
        super(application, model);
    }
}
