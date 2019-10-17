package com.showself.module.discover.ui.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.view.View;

import com.fly.tour.common.mvvm.BaseMvvmFragment;
import com.showself.module.discover.BR;
import com.showself.module.discover.R;
import com.showself.module.discover.databinding.FragmentDiscoverBinding;
import com.showself.module.discover.mvvm.factory.DiscoverViewModelFactory;
import com.showself.module.discover.mvvm.viewmodel.DiscoverViewModel;

/**
 * 首页——发现
 * Create by wangLei on 2019/10/14
 */

public class DiscoverFragment extends BaseMvvmFragment<FragmentDiscoverBinding, DiscoverViewModel> {
    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Override
    public Class onBindViewModel() {
        return DiscoverViewModel.class;
    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return DiscoverViewModelFactory.getInstance(mActivity.getApplication());
    }

    @Override
    public void initViewObservable() {

    }

    @Override
    public int onBindVariableId() {
        return com.showself.module.discover.BR.viewModel;
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_discover;
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
