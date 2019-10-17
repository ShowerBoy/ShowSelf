package com.showself.module.my.mvvm.factory;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.showself.module.my.mvvm.model.MyModel;
import com.showself.module.my.mvvm.viewmodel.MyViewModel;

public class MyViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile MyViewModelFactory INSTANCE;
    private final Application mApplication;

    public static MyViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (MyViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MyViewModelFactory(application);
                }
            }
        }
        return INSTANCE;
    }

    private MyViewModelFactory(Application application) {
        this.mApplication = application;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MyViewModel.class)) {
            return (T) new MyViewModel(mApplication, new MyModel(mApplication));
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
