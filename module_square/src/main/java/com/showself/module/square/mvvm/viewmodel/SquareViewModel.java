package com.showself.module.square.mvvm.viewmodel;

import android.app.Application;
import android.support.annotation.NonNull;

import com.fly.tour.api.dto.RespDTO;
import com.fly.tour.api.news.entity.NewsDetail;
import com.fly.tour.api.newstype.entity.NewsType;
import com.fly.tour.common.event.SingleLiveEvent;
import com.fly.tour.common.mvvm.viewmodel.BaseViewModel;
import com.showself.module.square.mvvm.model.SquareModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SquareViewModel extends BaseViewModel<SquareModel> {
    private SingleLiveEvent<List<NewsDetail>> mSquareSingleEvent;


    public SquareViewModel(@NonNull Application application, SquareModel model) {
        super(application, model);

    }

    public void getMainSquare(int id) {
        mModel.getMainSquare(id).subscribe(new Observer<RespDTO<List<NewsDetail>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RespDTO<List<NewsDetail>> listRespDTO) {
                getSquareSingleLiveEvent().postValue(listRespDTO.data);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public SingleLiveEvent<List<NewsDetail>> getSquareSingleLiveEvent() {
        return mSquareSingleEvent = createLiveData(mSquareSingleEvent);
    }
}
