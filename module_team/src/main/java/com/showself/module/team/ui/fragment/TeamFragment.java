package com.showself.module.team.ui.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.view.View;

import com.fly.tour.common.mvvm.BaseMvvmFragment;
import com.showself.module.team.BR;
import com.showself.module.team.R;
import com.showself.module.team.databinding.FragmentTeamBinding;
import com.showself.module.team.mvvm.factory.TeamViewModelFactory;
import com.showself.module.team.mvvm.viewmodel.TeamViewModel;

/**
 * 首页——护卫队
 * Create by wangLei on 2019/10/14
 */

public class TeamFragment extends BaseMvvmFragment<FragmentTeamBinding, TeamViewModel> {

    public static TeamFragment newInstance() {
        return new TeamFragment();
    }

    @Override
    public Class onBindViewModel() {
        return TeamViewModel.class;
    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return TeamViewModelFactory.getInstance(mActivity.getApplication());
    }

    @Override
    public void initViewObservable() {

    }

    @Override
    public int onBindVariableId() {
        return com.showself.module.team.BR.viewModel;
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_team;
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
