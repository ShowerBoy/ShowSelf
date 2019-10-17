package com.showself.module.rank.mvvm.factory;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.showself.module.rank.mvvm.model.RankModel;
import com.showself.module.rank.mvvm.viewmodel.RankViewModel;

public class RankViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile RankViewModelFactory INSTANCE;
    private final Application mApplication;

    public static RankViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (RankViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RankViewModelFactory(application);
                }
            }
        }
        return INSTANCE;
    }

    private RankViewModelFactory(Application application) {
        this.mApplication = application;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RankViewModel.class)) {
            return (T) new RankViewModel(mApplication, new RankModel(mApplication));
        }
        return super.create(modelClass);
    }
}
