package com.showself.module_login.mvvm.model;

import android.app.Application;

import com.fly.tour.api.RetrofitManager;
import com.fly.tour.api.dto.RespDTO;
import com.fly.tour.api.entity.BaseRep;
import com.fly.tour.api.entity.login.VcodeBean;
import com.fly.tour.api.http.RxAdapter;
import com.fly.tour.api.interfaces.LoginService;
import com.fly.tour.common.mvvm.model.BaseModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class LoginModel extends BaseModel {
    private LoginService mLoginService;

    public LoginModel(Application application) {
        super(application);
        mLoginService = RetrofitManager.getInstance().getLoginService();
    }

    public Observable<BaseRep<VcodeBean>> getVCode(Map<String, Object> splicing, String jsonstr) {
        return mLoginService.getVCode(splicing, jsonstr)
                .compose(RxAdapter.exceptionTransformer())
                .compose(RxAdapter.schedulersTransformer());
    }
}
