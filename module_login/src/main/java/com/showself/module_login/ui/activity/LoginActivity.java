package com.showself.module_login.ui.activity;

import android.Manifest;
import android.arch.lifecycle.ViewModelProvider;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fly.tour.common.mvvm.BaseMvvmActivity;
import com.fly.tour.common.util.ARouterPath;
import com.fly.tour.common.util.ToastUtil;
import com.showself.module_login.BR;
import com.showself.module_login.R;
import com.showself.module_login.databinding.ActivityLoginBinding;
import com.showself.module_login.mvvm.factory.LoginViewModelFactory;
import com.showself.module_login.mvvm.viewmodel.LoginViewModel;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;
/**
 *
 * Create by wangLei on 2019/10/17
 */

public class LoginActivity extends BaseMvvmActivity<ActivityLoginBinding, LoginViewModel> {
    @Override
    public Class onBindViewModel() {
        return LoginViewModel.class;
    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return LoginViewModelFactory.getInstance(getApplication());
    }


    @Override
    public void initViewObservable() {

    }

    @Override
    public int onBindVariableId() {
        return com.showself.module_login.BR.viewModel;
    }

    @Override
    public int onBindLayout() {
        return R.layout.activity_login;
    }

}
