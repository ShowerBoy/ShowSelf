package com.fly.tour.api.interfaces;

import com.fly.tour.api.entity.BaseRep;
import com.fly.tour.api.entity.login.VcodeBean;
import com.fly.tour.api.util.MethodUtils;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface LoginService {
//    @FormUrlEncoded
//    @POST("System/getVerificationCode")
//    Observable<RespDTO<List<VcodeBean>>> getVerificationCode(@Field("phone") String phone, @Field("username") String username, @Field("type") int type);

    @FormUrlEncoded
    @POST(MethodUtils.CHECK_ONLY_URL)
    Observable<BaseRep<VcodeBean>> getVCode(@QueryMap Map<String, Object> data, @Field("jsonstr") String jsonstr);
}
