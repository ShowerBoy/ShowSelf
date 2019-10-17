package com.fly.tour.main;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.fly.tour.common.mvvm.BaseMvvmActivity;
import com.fly.tour.common.util.ARouterPath;
import com.fly.tour.common.util.ToastUtil;
import com.fly.tour.main.mvvm.factory.MainViewModelFactory;
import com.fly.tour.main.mvvm.viewmodel.SplashViewModel;
import com.showself.module.main.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Description: <SplashActivity><br>
 * Author:      mxdl<br>
 * Date:        2019/6/22<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class SplashActivity extends BaseMvvmActivity<ViewDataBinding, SplashViewModel> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isTaskRoot()) {
            final Intent intent = getIntent();
            final String intentAction = intent.getAction();
            if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && intentAction != null && intentAction.equals(Intent.ACTION_MAIN)) {
                finish();
                return;
            }
        }
    }

    @Override
    public int onBindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

    }

    @Override
    public boolean enableToolbar() {
        return false;
    }

    @Override
    public void initData() {
//        mViewModel.login();
        mViewModel.guide();
    }

    public void startMainActivity() {
//        startActivity(new Intent(this, MainActivity.class));
//        finish();
        ARouter.getInstance()
                .build(ARouterPath.LoginMainAty)
                .navigation(SplashActivity.this);
        finish();
    }

    @Override
    public Class<SplashViewModel> onBindViewModel() {
        return SplashViewModel.class;
    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return MainViewModelFactory.getInstance(getApplication());
    }

    @Override
    public void initViewObservable() {
        mViewModel.getmVoidSingleLiveEvent().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void aVoid) {
                startMainActivity();
            }
        });
    }

    @Override
    public int onBindVariableId() {
        return 0;
    }
}