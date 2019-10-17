package com.showself.module.team.mvvm.factory;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.showself.module.team.mvvm.model.TeamModel;
import com.showself.module.team.mvvm.viewmodel.TeamViewModel;

public class TeamViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile TeamViewModelFactory INSTANCE;
    private final Application mApplication;

    public static TeamViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (TeamViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TeamViewModelFactory(application);
                }
            }
        }
        return INSTANCE;
    }

    private TeamViewModelFactory(Application application) {
        this.mApplication = application;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TeamViewModel.class)) {
            return (T) new TeamViewModel(mApplication, new TeamModel(mApplication));
        }
//        else if (modelClass.isAssignableFrom(NewsListViewModel.class)) {
//            return (T) new NewsListViewModel(mApplication, new NewsListModel(mApplication));
//        }else if (modelClass.isAssignableFrom(NewsTypeViewModel.class)) {
//            return (T) new NewsTypeViewModel(mApplication, new NewsTypeModel(mApplication));
//        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
