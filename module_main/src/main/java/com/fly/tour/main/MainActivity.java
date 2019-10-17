package com.fly.tour.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.fly.tour.common.mvvm.BaseActivity;
import com.fly.tour.common.provider.IDiscoverProvider;
import com.fly.tour.common.provider.IFindProvider;
import com.fly.tour.common.provider.IMeProvider;
import com.fly.tour.common.provider.IMyProvider;
import com.fly.tour.common.provider.INewsProvider;
import com.fly.tour.common.provider.IRankProvider;
import com.fly.tour.common.provider.ISquareProvider;
import com.fly.tour.common.provider.ITeamProvider;
import com.fly.tour.common.util.ARouterPath;
import com.fly.tour.main.entity.MainChannel;
import com.showself.module.main.R;

/**
 * Description: <BaseRefreshLayout><br>
 * Author:      mxdl<br>
 * Date:        2019/02/25<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
@Route(path = ARouterPath.MainAty)
public class MainActivity extends BaseActivity {
    @Autowired(name = ARouterPath.SquareFgt)
    ISquareProvider iSquareProvider;

    @Autowired(name = ARouterPath.RankFgt)
    IRankProvider iRankProvider;

    @Autowired(name = ARouterPath.TeamFgt)
    ITeamProvider iTeamProvider;

    @Autowired(name = ARouterPath.DiscoverFgt)
    IDiscoverProvider iDiscoverProvider;

    @Autowired(name = ARouterPath.MyFgt)
    IMyProvider iMyProvider;

    private Fragment mSquareFragment;
    private Fragment mRankFragment;
    private Fragment mTeamFragment;
    private Fragment mDiscoverFragment;
    private Fragment mMyFragment;
    private Fragment mCurrFragment;

    @Override
    public int onBindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int i = menuItem.getItemId();
                if (i == R.id.navigation_square) {
                    switchContent(mCurrFragment, mSquareFragment, MainChannel.SQUARE.name);
                    mCurrFragment = mSquareFragment;

                    return true;
                } else if (i == R.id.navigation_rank) {
                    switchContent(mCurrFragment, mRankFragment, MainChannel.RANK.name);
                    mCurrFragment = mRankFragment;

                    return true;
                } else if (i == R.id.navigation_team) {
                    switchContent(mCurrFragment, mTeamFragment, MainChannel.TEAM.name);
                    mCurrFragment = mTeamFragment;

                    return true;
                } else if (i == R.id.navigation_discover) {
                    switchContent(mCurrFragment, mDiscoverFragment, MainChannel.TEAM.name);
                    mCurrFragment = mDiscoverFragment;

                    return true;
                } else if (i == R.id.navigation_my) {
                    switchContent(mCurrFragment, mMyFragment, MainChannel.MY.name);
                    mCurrFragment = mMyFragment;

                    return true;
                }
                return false;
            }
        });
        if(iSquareProvider != null){
            mSquareFragment = iSquareProvider.getSquareFragment();
        }
        if(iRankProvider != null){
            mRankFragment = iRankProvider.getRankFragment();
        }
        if(iTeamProvider != null){
            mTeamFragment = iTeamProvider.getTeamFragment();
        }
        if(iDiscoverProvider != null){
            mDiscoverFragment = iDiscoverProvider.getDiscoverFragment();
        }
        if(iMyProvider != null){
            mMyFragment = iMyProvider.getMyFragment();
        }
        mCurrFragment = mSquareFragment;
        if(mSquareFragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, mSquareFragment, MainChannel.SQUARE.name).commit();
        }
    }

    @Override
    public void initData() {

    }

    public void switchContent(Fragment from, Fragment to, String tag) {
        if (from == null || to == null) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!to.isAdded()) {
            transaction.hide(from).add(R.id.frame_content, to, tag).commit();
        } else {
            transaction.hide(from).show(to).commit();
        }
    }
}
