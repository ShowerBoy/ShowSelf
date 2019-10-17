package com.showself.module.rank.ui.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.view.View;

import com.fly.tour.common.mvvm.BaseMvvmFragment;
import com.showself.module.rank.BR;
import com.showself.module.rank.R;
import com.showself.module.rank.databinding.FragmentRankBinding;
import com.showself.module.rank.mvvm.factory.RankViewModelFactory;
import com.showself.module.rank.mvvm.viewmodel.RankViewModel;

/**
 * 首页——排行
 * Create by wangLei on 2019/10/14
 */

public class RankFragment extends BaseMvvmFragment<FragmentRankBinding, RankViewModel> {

    public static RankFragment newInstance() {
        return new RankFragment();
    }

    @Override
    public Class onBindViewModel() {
        return RankViewModel.class;
    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return RankViewModelFactory.getInstance(mActivity.getApplication());
    }

    @Override
    public void initViewObservable() {

    }

    @Override
    public int onBindVariableId() {
        return com.showself.module.rank.BR.viewModel;
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_rank;
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
