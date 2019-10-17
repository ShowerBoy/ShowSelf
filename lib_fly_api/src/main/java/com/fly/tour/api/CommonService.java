package com.fly.tour.api;

import com.fly.tour.api.dto.RespDTO;
import com.fly.tour.api.user.LoginDTO;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CommonService {
    @POST("/user/login")
    Observable<RespDTO<LoginDTO>> login(@Query("username") String name, @Query("password") String pwd);


}
