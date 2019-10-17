package com.showself.module.my.ui.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.view.View;

import com.fly.tour.common.mvvm.BaseMvvmFragment;
import com.showself.module.my.BR;
import com.showself.module.my.R;
import com.showself.module.my.databinding.FragmentMyBinding;
import com.showself.module.my.mvvm.factory.MyViewModelFactory;
import com.showself.module.my.mvvm.viewmodel.MyViewModel;

/**
 * 首页——我的
 * Create by wangLei on 2019/10/14
 */

public class MyFragment extends BaseMvvmFragment<FragmentMyBinding, MyViewModel> {
    public static MyFragment newInstance() {
        return new MyFragment();
    }

    @Override
    public Class onBindViewModel() {
        return MyViewModel.class;
    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return MyViewModelFactory.getInstance(mActivity.getApplication());
    }

    @Override
    public void initViewObservable() {

    }

    @Override
    public int onBindVariableId() {
        return com.showself.module.my.BR.viewModel;
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {

    }

    @Override
    public String getToolbarTitle() {
        return null;
    }
}
