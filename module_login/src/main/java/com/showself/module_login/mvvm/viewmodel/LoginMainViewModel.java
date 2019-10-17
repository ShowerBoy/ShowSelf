package com.showself.module_login.mvvm.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fly.tour.common.mvvm.model.BaseModel;
import com.fly.tour.common.mvvm.viewmodel.BaseViewModel;
import com.fly.tour.common.util.ToastUtil;
import com.showself.module_login.mvvm.model.LoginModel;
import com.showself.module_login.ui.activity.LoginActivity;
import com.showself.module_login.ui.activity.RegisterActivity;

import java.util.Observable;

public class LoginMainViewModel extends BaseViewModel<LoginModel> {

    public LoginMainViewModel(@NonNull Application application, LoginModel model) {
        super(application, model);

    }

    /**
     * 快速注册
     */
    public void onQuickRegisterClick() {
//        postStartActivityEvent(RegisterActivity.class, null);
    }

    /**
     * 账号登录
     */
    public void onAccountLoginClick() {
        postStartActivityEvent(LoginActivity.class, null);
    }
}
