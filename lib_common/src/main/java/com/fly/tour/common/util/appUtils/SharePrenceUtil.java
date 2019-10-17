package com.fly.tour.common.util.appUtils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.fly.tour.common.BaseApplication;
import com.fly.tour.common.entity.LoginResultInfo;
import com.fly.tour.common.entity.SystemInfo;

public class SharePrenceUtil {
    private static LoginResultInfo loginInfo;
    /**
     * 获取用户每次登陆的信息
     */
    public static LoginResultInfo getLonginInfo(Context context) {
        if (loginInfo != null) {
            return loginInfo;
        } else {
            loginInfo = new LoginResultInfo();
        }
        SharedPreferences loginJson = context.getSharedPreferences(
                "login_info", Activity.MODE_PRIVATE);
        loginInfo.setSessionId(loginJson.getString("sessionId", null));
        loginInfo.setUserName(loginJson.getString("userName", null));
        loginInfo.setGender(loginJson.getInt("gender", 0));
        loginInfo.setUserId(loginJson.getInt("userId", 0));
        loginInfo.setShowid(loginJson.getInt("showid", 0));
        loginInfo.setAlterpwd(loginJson.getInt("alterpwd", 0));
        loginInfo.setEmail(loginJson.getString("email", null));
        loginInfo.setAvatar(loginJson.getString("avatar", null));
        loginInfo.setPhotoNum(loginJson.getInt("photoNum", 0));
        loginInfo.setMobile(loginJson.getString("mobile", null));
        loginInfo.setAccountString(loginJson.getString("accountString", null));
        loginInfo.setEmailstatus(loginJson.getInt("emailstatus", 0));
        loginInfo.setMobilestatus(loginJson.getInt("mobilestatus", 0));
        loginInfo.setNotificationInterval(loginJson.getInt(
                "notificationInterval", 0));
        loginInfo.setSkyVersion(loginJson.getInt("skyVersion", 0));
        loginInfo.setSkey(loginJson.getString("skey", null));
        loginInfo.setAppVersion(loginJson.getString("appVersion", null));
        loginInfo.setAppUrl(loginJson.getString("appUrl", null));
        loginInfo.setXmppPort(loginJson.getInt("xmppPort", 0));
        loginInfo.setXmppServer(loginJson.getString("xmppServer", null));
        loginInfo.setClient_timeout(loginJson.getInt("client_timeout", 0));
        loginInfo.setClient_retry_times(loginJson.getInt("client_retry_times",
                0));
        loginInfo.setSinaWeiboId(loginJson.getString("sinaWeiboId", null));
        loginInfo.setQqOpenId(loginJson.getString("qqOpenId", null));
        loginInfo.setRenrenId(loginJson.getString("renrenId", null));
        loginInfo.setLoginType(loginJson.getInt("loginType", 0));
        loginInfo.setAccessToken(loginJson.getString("accessToken", null));
        loginInfo.discover_index = loginJson.getInt("discover_index", 0);
        loginInfo.homepage_index = loginJson.getInt("homepage_index", 0);
        loginInfo.liveshow_index = loginJson.getInt("liveshow_index", 0);
        loginInfo.messagebox_index = loginJson.getInt("messagebox_index", 0);
        loginInfo.setIntro(loginJson.getString("intro", ""));
        loginInfo.setTaskDef(loginJson.getInt("taskDef", 0));
        loginInfo.setCr_hours(loginJson.getLong("cr_hours", 0));
        loginInfo.setFansListArray(loginJson.getString("fan_board_def", ""));
        loginInfo.setSpvswitch(loginJson.getInt("spvswitch", 0));
        loginInfo.setCredit_level(loginJson.getInt("credit_level", 0));
        return loginInfo;
    }


    /**
     * 保存每次获取的系统信息
     */
    public static void SaveSystemInfo(Context context, SystemInfo systemInfo) {
        SharedPreferences preferences = context.getSharedPreferences(
                "system_info", Activity.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putString("sKeyString", systemInfo.getsKeyString());// 加密解密的key,16个字母
        editor.putInt("sKeyVersion", systemInfo.getsKeyVersion());// 加密解密的key的version
        editor.putInt("defaultTimeOutSeconds",
                systemInfo.getDefaultTimeOutSeconds());// 默认超时时间
        editor.putInt("repeatRequestCount", systemInfo.getRepeatRequestCount());// 重连次数
        editor.commit();
    }

    /**
     * 获取每次获取的系统信息 //terminal 默认为2
     */
    public static SystemInfo getSystemInfo(Context context) {
        SystemInfo systemInfo = SystemInfo.getShareSystem();
        SharedPreferences loginJson = context.getSharedPreferences(
                "system_info", Activity.MODE_PRIVATE);
        systemInfo.setsKeyString(loginJson.getString("sKeyString",
                "aaaaaaaaaaaaaaaa"));// key 值
        systemInfo.setsKeyVersion(loginJson.getInt("sKeyVersion", 5));// key
        // version
        systemInfo.setDefaultTimeOutSeconds(loginJson.getInt(
                "defaultTimeOutSeconds", 15));// 请求超时时间
        systemInfo.setRepeatRequestCount(loginJson.getInt("repeatRequestCount",
                1));// 重复请求
        return systemInfo;
    }

    /**
     * 获取每次获取的本地Md5值
     */
    public static String getLocalMd5(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(
                "md5_info", Activity.MODE_PRIVATE);
        return preferences.getString("md5", "");
    }

    public static void saveIDFA(String name) {
        SharedPreferences sp = BaseApplication.getInstance()
                .getApplicationContext()
                .getSharedPreferences("note", Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString("nm", name);
        editor.commit();
    }

    public static String getIDFA() {
        SharedPreferences sp = BaseApplication.getInstance()
                .getApplicationContext()
                .getSharedPreferences("note", Context.MODE_PRIVATE);
        return sp.getString("nm", "");
    }


    public static void savePUBIDFA(String name) {
        SharedPreferences sp = BaseApplication.getInstance()
                .getApplicationContext()
                .getSharedPreferences("note", Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString("pub_idfa", name);
        editor.commit();
    }

    public static String getPUBIDFA() {
        SharedPreferences sp = BaseApplication.getInstance()
                .getApplicationContext()
                .getSharedPreferences("note", Context.MODE_PRIVATE);
        return sp.getString("pub_idfa", "");
    }


}
