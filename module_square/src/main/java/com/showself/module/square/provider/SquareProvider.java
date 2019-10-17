package com.showself.module.square.provider;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fly.tour.common.provider.ISquareProvider;
import com.fly.tour.common.util.ARouterPath;
import com.showself.module.square.ui.fragment.SquareFragment;

@Route(path = ARouterPath.SquareFgt, name = "广场")
public class SquareProvider implements ISquareProvider {
    @Override
    public Fragment getSquareFragment() {
        return SquareFragment.newInstance();
    }

    @Override
    public void init(Context context) {

    }
}
