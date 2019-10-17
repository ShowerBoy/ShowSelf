package com.showself.module_login.mvvm.factory;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.showself.module_login.mvvm.model.LoginModel;
import com.showself.module_login.mvvm.viewmodel.LoginMainViewModel;
import com.showself.module_login.mvvm.viewmodel.LoginViewModel;

public class LoginViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile LoginViewModelFactory INSTANCE;
    private final Application mApplication;

    public static LoginViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (LoginViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LoginViewModelFactory(application);
                }
            }
        }
        return INSTANCE;
    }

    private LoginViewModelFactory(Application application) {
        this.mApplication = application;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(mApplication, new LoginModel(mApplication));
        } else if (modelClass.isAssignableFrom(LoginMainViewModel.class)) {
            return (T) new LoginMainViewModel(mApplication, new LoginModel(mApplication));
        }
//        else if (modelClass.isAssignableFrom(NewsListViewModel.class)) {
//            return (T) new NewsListViewModel(mApplication, new NewsListModel(mApplication));
//        }else if (modelClass.isAssignableFrom(NewsTypeViewModel.class)) {
//            return (T) new NewsTypeViewModel(mApplication, new NewsTypeModel(mApplication));
//        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
