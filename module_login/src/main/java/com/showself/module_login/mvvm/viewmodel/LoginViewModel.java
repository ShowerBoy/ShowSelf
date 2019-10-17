package com.showself.module_login.mvvm.viewmodel;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.fly.tour.api.encrypt.CipherUtil;
import com.fly.tour.api.entity.BaseRep;
import com.fly.tour.api.http.ApiException;
import com.fly.tour.api.util.ApiUtils;
import com.fly.tour.common.CommonUtils;
import com.fly.tour.common.mvvm.viewmodel.BaseViewModel;
import com.fly.tour.common.util.NetUtil;
import com.fly.tour.common.util.SPUtils;
import com.fly.tour.common.util.ToastUtil;
import com.fly.tour.api.entity.login.VcodeBean;
import com.showself.module_login.mvvm.model.LoginModel;
import com.showself.utils.Utils;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginViewModel extends BaseViewModel<LoginModel> {
    public ObservableField<String> phone = new ObservableField<>();
    public ObservableField<String> serialNumber = new ObservableField<>();

    public LoginViewModel(@NonNull Application application, LoginModel model) {
        super(application, model);
        serialNumber.set("流水号：");
        phone.set(SPUtils.get(getApplication(), "phone", "").toString());
        phone.set("13703903621");
        Utils utils = new Utils();
    }


    public void onGetVCodeClick() {
        if (!NetUtil.checkNetToast()) {
            postShowNetWorkErrViewEvent(true);
            return;
        }
        if (TextUtils.isEmpty(phone.get())) {
            ToastUtil.showToast("请输入手机号！");
            return;
        }
        String dataString = CommonUtils.newInstance().jsonstr(getApplication(), phone.get());
        Map<String, Object> map = CommonUtils.newInstance().getSplicingMap(getApplication(), dataString);
        if (map == null) {
            return;
        }
        String jsonstr = null;
        try {
            jsonstr = CipherUtil.encryptData(dataString, Utils.getAesKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mModel.getVCode(map, jsonstr).subscribe(new Observer<BaseRep<VcodeBean>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseRep<VcodeBean> objectBaseRep) {
                ToastUtil.showToast(objectBaseRep.getStatus().getMessage());
                Object data = objectBaseRep.getData();
            }

            @Override
            public void onError(Throwable e) {
                ApiException apiException = (ApiException) e.getCause();
                ToastUtil.showToast(apiException.getMessage());
            }

            @Override
            public void onComplete() {
                ToastUtil.showToast("请求完毕");
            }
        });


//                .subscribe(new Observer<RespDTO<List<VcodeBean>>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(RespDTO<List<VcodeBean>> vcodeBeanRespDTO) {
//                serialNumber.set(vcodeBeanRespDTO.getStatus() + "");
//                SPUtils.put(getApplication(), "phone", phone.get());
////                ARouter.getInstance().build(ARouterPath.MainAty).navigation(getApplication());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                ToastUtil.showToast(e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                ToastUtil.showToast("请求完毕");
//                ARouter.getInstance().build(ARouterPath.MainAty).navigation();
//                postFinishActivityEvent();
//            }
//        });

    }

}
