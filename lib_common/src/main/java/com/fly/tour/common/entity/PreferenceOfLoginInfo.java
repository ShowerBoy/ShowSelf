package com.fly.tour.common.entity;

import android.app.Activity;
import android.content.SharedPreferences;

import com.fly.tour.common.BaseApplication;

public class PreferenceOfLoginInfo {
    public static PreferenceOfLoginInfo instance;
    private String mySharedPreferenceName = "myLoginInfo";

    private PreferenceOfLoginInfo() {
    }

    public static PreferenceOfLoginInfo getInstance() {
        if (instance == null) {
            instance = new PreferenceOfLoginInfo();
        }
        return instance;
    }
    /**
     * 更新登录信息 （一键登录更新账号）
     */
    public void updateOneKeyLoginInfo(String account) {
        SharedPreferences sp = BaseApplication.getInstance().getSharedPreferences(this.mySharedPreferenceName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("one_key_openId", account);
        editor.commit();
    }
}
