package com.showself.module.square.mvvm.factory;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.showself.module.square.mvvm.model.SquareModel;
import com.showself.module.square.mvvm.viewmodel.SquareViewModel;

public class SquareViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile SquareViewModelFactory INSTANCE;
    private final Application mApplication;

    public static SquareViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (SquareViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SquareViewModelFactory(application);
                }
            }
        }
        return INSTANCE;
    }

    private SquareViewModelFactory(Application application) {
        this.mApplication = application;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SquareViewModel.class)) {
            return (T) new SquareViewModel(mApplication, new SquareModel(mApplication));
        }
//        else if (modelClass.isAssignableFrom(NewsListViewModel.class)) {
//            return (T) new NewsListViewModel(mApplication, new NewsListModel(mApplication));
//        }else if (modelClass.isAssignableFrom(NewsTypeViewModel.class)) {
//            return (T) new NewsTypeViewModel(mApplication, new NewsTypeModel(mApplication));
//        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
