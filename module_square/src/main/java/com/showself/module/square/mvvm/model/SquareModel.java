package com.showself.module.square.mvvm.model;

import android.app.Application;

import com.fly.tour.api.RetrofitManager;
import com.fly.tour.api.dto.RespDTO;
import com.fly.tour.api.http.RxAdapter;
import com.fly.tour.api.interfaces.SquareService;
import com.fly.tour.api.news.entity.NewsDetail;
import com.fly.tour.common.mvvm.model.BaseModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * 广场首页请求
 */
public class SquareModel extends BaseModel {
    private SquareService mSquareService;

    public SquareModel(Application application) {
        super(application);
        mSquareService = RetrofitManager.getInstance().getSquareService();
    }

    public Observable<RespDTO<List<NewsDetail>>> getMainSquare(int id) {
        return mSquareService.getMainSquare(RetrofitManager.getInstance().TOKEN, id)
                .compose(RxAdapter.exceptionTransformer())
                .compose(RxAdapter.schedulersTransformer());
    }
}
