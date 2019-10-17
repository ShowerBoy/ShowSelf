package com.showself.module_login.ui.activity;

import android.arch.lifecycle.ViewModelProvider;

import com.fly.tour.common.mvvm.BaseMvvmActivity;
import com.showself.module_login.R;

/**
 * 注册
 * Create by wangLei on 2019/10/17
 */

public class RegisterActivity extends BaseMvvmActivity {
    @Override
    public Class onBindViewModel() {
        return null;
    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return null;
    }

    @Override
    public void initViewObservable() {

    }

    @Override
    public int onBindVariableId() {
        return 0;
    }

    @Override
    public int onBindLayout() {
        return R.layout.activity_register;
    }
}
