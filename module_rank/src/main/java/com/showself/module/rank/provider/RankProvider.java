package com.showself.module.rank.provider;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fly.tour.common.provider.IRankProvider;
import com.fly.tour.common.util.ARouterPath;
import com.showself.module.rank.ui.fragment.RankFragment;

@Route(path = ARouterPath.RankFgt, name = "排行")
public class RankProvider implements IRankProvider {

    @Override
    public Fragment getRankFragment() {
        return RankFragment.newInstance();
    }

    @Override
    public void init(Context context) {

    }
}
