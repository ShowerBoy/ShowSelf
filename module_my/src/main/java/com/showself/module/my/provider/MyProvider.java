package com.showself.module.my.provider;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.fly.tour.common.provider.IMyProvider;
import com.fly.tour.common.util.ARouterPath;
import com.showself.module.my.ui.fragment.MyFragment;

@Route(path = ARouterPath.MyFgt, name = "我的")
public class MyProvider implements IMyProvider {
    @Override
    public Fragment getMyFragment() {
        return MyFragment.newInstance();
    }

    @Override
    public void init(Context context) {

    }
}
