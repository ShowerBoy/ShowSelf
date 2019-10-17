package com.fly.tour.common.entity;

import android.app.Application;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.fly.tour.common.BaseApplication;
import com.fly.tour.common.util.appUtils.Utils;


public class SystemInfo {

    private static SystemInfo shareSystem = null;
    private int terminal;           //终端 2-android
    private String systemVersion;   //
    private String imei;
    private String imsi;
    private String appVersion;      //当前版本信息
    private String a_appver;
    private String idfa;//辅助判断设备唯一标示
    private String baidu_userid;//
    private String baidu_channelid;//
    private String macaddr;
    private String sensor;//陀螺仪数据
    private String sKeyString;      //加密解密的key,16个字母
    private int sKeyVersion;        //加密解密的key的version
    private int defaultTimeOutSeconds; //默认超时时间
    private int repeatRequestCount; //重连次数
    private String pub_idfa;


    private SystemInfo() {
        idfa = Utils.getOrMakeIMEI();
//        DLog.d("testidfa", idfa);
        pub_idfa = Utils.getPubIdfa();
//        DLog.d("testidfa", pub_idfa);
        TelephonyManager tm = (TelephonyManager) BaseApplication.getInstance().getSystemService(Context.TELEPHONY_SERVICE);


        try {
            /*
             * 唯一的设备ID：
             * GSM手机的 IMEI 和 CDMA手机的 MEID.
             * Return null if device ID is not available.
             */
            imei = tm.getDeviceId();//String

            /*
             * 唯一的用户ID：
             * 例如：IMSI(国际移动用户识别码) for a GSM phone.
             * 需要权限：READ_PHONE_STATE
             */
            imsi = tm.getSubscriberId();//String
        } catch (Exception e) {
//            DLog.d("systeminfo", e.toString());
        }

        if (TextUtils.isEmpty(imei)) {
            imei = idfa;
        }

        if (TextUtils.isEmpty(imsi)) {
            imsi = "";
        }
        /*
         * android系统版本号
         *
         */
        systemVersion = android.os.Build.VERSION.RELEASE;


        terminal = 2;
        this.appVersion = "1.5.0";
        this.defaultTimeOutSeconds = 15;
        this.repeatRequestCount = 1;
        this.a_appver = Utils.getPackageInfo(BaseApplication.getInstance().getApplicationContext()).versionName;
        ;
        this.sKeyVersion = 61;
        this.sKeyString = "aaaaaaaaaaaaaaaa";
        String idstr = "";
        if (idstr != null) {
            baidu_channelid = idstr;
        }

        this.macaddr = Utils.getMac(BaseApplication.getInstance().getApplicationContext());
    }

    public static SystemInfo getShareSystem() {
        if (shareSystem == null) {
            shareSystem = new SystemInfo();
        }
        return shareSystem;
    }

    public static void setShareSystem(SystemInfo shareSystem) {
        SystemInfo.shareSystem = shareSystem;
    }

    public String getPub_idfa() {
        return pub_idfa;
    }

    public String getMacaddr() {
        return macaddr;
    }

    public int getTerminal() {
        return terminal;
    }

    public void setTerminal(int terminal) {
        this.terminal = terminal;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getImei() {
        return imei;
    }

    public String getImsi() {
        return imsi;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getsKeyString() {
        return sKeyString;
    }

    public void setsKeyString(String sKeyString) {
        this.sKeyString = sKeyString;
    }

    public int getsKeyVersion() {
        return sKeyVersion;
    }

    public void setsKeyVersion(int sKeyVersion) {
        this.sKeyVersion = sKeyVersion;
    }

    public int getDefaultTimeOutSeconds() {
        return defaultTimeOutSeconds;
    }

    public void setDefaultTimeOutSeconds(int defaultTimeOutSeconds) {
        this.defaultTimeOutSeconds = defaultTimeOutSeconds;
    }

    public int getRepeatRequestCount() {
        return repeatRequestCount;
    }

    public void setRepeatRequestCount(int repeatRequestCount) {
        this.repeatRequestCount = repeatRequestCount;
    }

    public String getA_appver() {
        return a_appver;
    }


    public void setA_appver(String aAppver) {
        a_appver = aAppver;
    }

    public String getIdfa() {
        return idfa;
    }

    public String getBaidu_userid() {
        return baidu_userid;
    }

    public void setBaidu_userid(String baidu_userid) {
        this.baidu_userid = baidu_userid;
    }

    public String getBaidu_channelid() {
        return baidu_channelid;
    }

    public void setBaidu_channelid(String baidu_channelid) {
        this.baidu_channelid = baidu_channelid;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }
}
