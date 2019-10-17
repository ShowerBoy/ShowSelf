package com.showself.module.square.ui.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.Nullable;
import android.view.View;

import com.fly.tour.api.news.entity.NewsDetail;
import com.fly.tour.common.mvvm.BaseMvvmFragment;
import com.showself.module.square.BR;
import com.showself.module.square.R;
import com.showself.module.square.databinding.FragmentSquareBinding;
import com.showself.module.square.mvvm.factory.SquareViewModelFactory;
import com.showself.module.square.mvvm.viewmodel.SquareViewModel;

import java.util.List;

/**
 * 首页——广场
 * Create by wangLei on 2019/10/14
 */

public class SquareFragment extends BaseMvvmFragment<FragmentSquareBinding, SquareViewModel> {

    public static SquareFragment newInstance() {
        return new SquareFragment();
    }

    @Override
    public Class onBindViewModel() {
        return SquareViewModel.class;
    }

    @Override
    public ViewModelProvider.Factory onBindViewModelFactory() {
        return SquareViewModelFactory.getInstance(mActivity.getApplication());
    }

    @Override
    public void initViewObservable() {
        mViewModel.getSquareSingleLiveEvent().observe(this, new Observer<List<NewsDetail>>() {
            @Override
            public void onChanged(@Nullable List<NewsDetail> newsDetails) {
                //成功后的回调

            }
        });
    }

    @Override
    public int onBindVariableId() {
        return com.showself.module.square.BR.viewModel;
    }

    @Override
    public int onBindLayout() {
        return R.layout.fragment_square;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {
        mViewModel.getMainSquare(1);
    }

    @Override
    public String getToolbarTitle() {
        return null;
    }
}
