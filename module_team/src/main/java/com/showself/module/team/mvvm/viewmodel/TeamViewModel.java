package com.showself.module.team.mvvm.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fly.tour.common.mvvm.viewmodel.BaseViewModel;
import com.showself.module.team.mvvm.model.TeamModel;

public class TeamViewModel extends BaseViewModel<TeamModel> {

    public TeamViewModel(@NonNull Application application, TeamModel model) {
        super(application, model);
    }
}
