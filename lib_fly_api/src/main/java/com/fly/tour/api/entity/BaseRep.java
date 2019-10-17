package com.fly.tour.api.entity;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * 统一返回格式
 * Create by wangLei on 2019/10/15
 */

public class BaseRep<T> implements Serializable {
    public String appver;
    public Status status;
    public T data;

    @NonNull
    @Override
    public String toString() {
        return "BaseRep{" +
                "appver=" + appver +
                ", statuscode='" + status.getStatuscode() + '\'' +
                ", message='" + status.getMessage() + '\'' +
                ", data=" + data +
                '}';
    }

    public String getAppver() {
        return appver;
    }

    public void setAppver(String appver) {
        this.appver = appver;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
