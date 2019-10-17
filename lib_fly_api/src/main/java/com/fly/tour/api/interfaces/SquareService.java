package com.fly.tour.api.interfaces;

import com.fly.tour.api.config.API;
import com.fly.tour.api.dto.RespDTO;
import com.fly.tour.api.news.entity.NewsDetail;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 广场Service
 */
public interface SquareService {
    @POST(API.URL_HOST_NEWS + "/newsdetail/query/all")
    Observable<RespDTO<List<NewsDetail>>> getMainSquare(@Header("Authorization") String token, @Query("id") int id);

}
