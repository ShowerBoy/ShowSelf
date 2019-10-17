package com.showself.module.team.provider;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.fly.tour.common.provider.ITeamProvider;
import com.fly.tour.common.util.ARouterPath;
import com.showself.module.team.ui.fragment.TeamFragment;

@Route(path = ARouterPath.TeamFgt, name = "护卫队")
public class TeamProvider implements ITeamProvider {
    @Override
    public Fragment getTeamFragment() {
        return TeamFragment.newInstance();
    }

    @Override
    public void init(Context context) {

    }
}
