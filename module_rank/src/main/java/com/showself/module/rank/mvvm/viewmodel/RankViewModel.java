package com.showself.module.rank.mvvm.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fly.tour.common.mvvm.viewmodel.BaseViewModel;
import com.showself.module.rank.mvvm.model.RankModel;

public class RankViewModel extends BaseViewModel<RankModel> {

    public RankViewModel(@NonNull Application application, RankModel model) {
        super(application, model);
    }
}
