package com.showself.module.discover.mvvm.factory;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.showself.module.discover.mvvm.model.DiscoverModel;
import com.showself.module.discover.mvvm.viewmodel.DiscoverViewModel;

public class DiscoverViewModelFactory extends ViewModelProvider.NewInstanceFactory{
    @SuppressLint("StaticFieldLeak")
    private static volatile DiscoverViewModelFactory INSTANCE;
    private final Application mApplication;

    public static DiscoverViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (DiscoverViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DiscoverViewModelFactory(application);
                }
            }
        }
        return INSTANCE;
    }

    private DiscoverViewModelFactory(Application application) {
        this.mApplication = application;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DiscoverViewModel.class)) {
            return (T) new DiscoverViewModel(mApplication, new DiscoverModel(mApplication));
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
