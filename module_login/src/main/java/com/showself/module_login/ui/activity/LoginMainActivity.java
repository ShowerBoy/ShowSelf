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
import com.showself.module_login.databinding.ActivityLoginMainBinding;
import com.showself.module_login.mvvm.factory.LoginViewModelFactory;
import com.showself.module_login.mvvm.viewmodel.LoginMainViewModel;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * 登录选择页
 * Create by wangLei on 2019/10/17
 */

@Route(path = ARouterPath.LoginMainAty)
public class LoginMainActivity extends BaseMvvmActivity<ActivityLoginMainBinding, LoginMainViewModel> {
    @Override
    public Class onBindViewModel() {
        return LoginMainViewModel.class;
    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return LoginViewModelFactory.getInstance(getApplication());
    }

    @Override
    public void initViewObservable() {

    }

    @Override
    public void initData() {
        super.initData();
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_SETTINGS,
                Manifest.permission.CAMERA)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            ToastUtil.showToast("获取权限成功");
                            Log.d(TAG, permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            ToastUtil.showToast("获取权限失败");
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时。还会提示请求权限的对话框
                            Log.d(TAG, permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，而且选中『不再询问』
                            ToastUtil.showToast("获取权限失败");
                            Log.d(TAG, permission.name + " is denied.");
                        }
                    }
                });
    }

    @Override
    public int onBindVariableId() {
        return com.showself.module_login.BR.viewModel;
    }

    @Override
    public int onBindLayout() {
        return R.layout.activity_login_main;
    }
}
