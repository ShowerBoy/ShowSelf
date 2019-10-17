package com.fly.tour.common.provider;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.template.IProvider;

public interface ISquareProvider extends IProvider {
    Fragment getSquareFragment();
}
