package com.showself.module.discover.provider;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fly.tour.common.provider.IDiscoverProvider;
import com.fly.tour.common.util.ARouterPath;
import com.showself.module.discover.ui.fragment.DiscoverFragment;

@Route(path = ARouterPath.DiscoverFgt, name = "发现")
public class DiscoverProvider implements IDiscoverProvider {
    @Override
    public Fragment getDiscoverFragment() {
        return DiscoverFragment.newInstance();
    }

    @Override
    public void init(Context context) {

    }
}
