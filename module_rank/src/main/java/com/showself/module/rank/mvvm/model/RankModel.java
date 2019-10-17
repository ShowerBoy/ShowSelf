package com.showself.module.rank.mvvm.model;

import android.app.Application;

import com.fly.tour.api.RetrofitManager;
import com.fly.tour.api.interfaces.RankService;
import com.fly.tour.common.mvvm.model.BaseModel;

public class RankModel extends BaseModel {
    private RankService mRankService;

    public RankModel(Application application) {
        super(application);
        mRankService = RetrofitManager.getInstance().getRankService();
    }
}
