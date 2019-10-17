package com.fly.tour.common;

import android.content.Context;

import com.fly.tour.api.BuildConfig;
import com.fly.tour.api.config.API;
import com.fly.tour.api.encrypt.CipherUtil;
import com.fly.tour.api.util.ApiUtils;
import com.fly.tour.api.util.MethodUtils;
import com.fly.tour.api.util.SignUtil;
import com.fly.tour.common.entity.SystemInfo;
import com.fly.tour.common.util.ToastUtil;
import com.fly.tour.common.util.appUtils.NetUtil;
import com.fly.tour.common.util.appUtils.SharePrenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.Arrays.sort;

public class CommonUtils {
    /**
     * 维度
     */
    public static double getLatitude = 0;
    /**
     * 经度
     */
    public static double getLongitude = 0;

    public static CommonUtils commonUtils;

    public static CommonUtils newInstance() {
        if (commonUtils == null) {
            commonUtils = new CommonUtils();
        }
        return commonUtils;
    }

    /**
     * 获取请求的dataString
     *
     * @param context
     * @param key
     * @return
     */
    public String jsonstr(Context context, String key) {
        JSONObject date = new JSONObject();
        String dataString = "";
        try {
            addLocationAndSession(date, true, true, false, context);
            date.put("key", key);
            date.put("type", "mobile");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", date);
            dataString = jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dataString;
    }

    public Map<String, Object> getSplicingMap(Context context, String dataString) {
//        SystemInfo systemInfo = SharePrenceUtil.getSystemInfo(context);
//        JSONObject date = new JSONObject();
//        String dataString = "";
//        try {
//            addLocationAndSession(date, true, true, false, context);
//            date.put("key", key);
//            date.put("type", "mobile");
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("data", date);
//            dataString = jsonObject.toString();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        if (!ApiUtils.isConnectingToInternet(context)) {
            ToastUtil.showToast("请检查网络");
            return null;
        }

        StringBuffer sbUrl = new StringBuffer();
        sbUrl.append(API.BASEURL);
        sbUrl.append(MethodUtils.CHECK_ONLY_URL);
        int skeyver = SharePrenceUtil.getSystemInfo(BaseApplication.getInstance().getApplicationContext()).getsKeyVersion();
        sbUrl.append("?skeyver=" + skeyver + "&is_android=1");
        String path = "/inf/" + MethodUtils.CHECK_ONLY_URL;
        String url = sbUrl.toString();

        String sign = SignUtil.getSign(url, path, dataString);

        Map<String, Object> splicingMap = new HashMap<>();
        splicingMap.put("skeyver", SharePrenceUtil.getSystemInfo(context).getsKeyString());
        splicingMap.put("is_android", 1);
        splicingMap.put("appId", "showself");
        splicingMap.put("sign", sign);
        splicingMap.put("timestamp", System.currentTimeMillis() + "");
        return splicingMap;
    }


    private static String getPath(String url) {
        // php
        if (url.contains("/inf") && url.contains("?") && url.indexOf("/inf") < url.indexOf("?")) {
            return url.substring(url.indexOf("/inf"), url.indexOf("?"));
        }

        // java
        String[] arrStr = new String[]{"v2", "coreservice", "armyservice", "games", "dynspacemsg"};
        for (int i = 0; i < arrStr.length; i++) {
            if (checkUrl(url, arrStr[i])) {
                return url.substring(url.indexOf(arrStr[i]) + arrStr[i].length(), url.indexOf("?"));
            }
        }
        return null;
    }

    private static boolean checkUrl(String url, String checker) {
        return url.contains(checker) && url.contains("?") && url.indexOf(checker) < url.indexOf("?");
    }

    /**
     * 添加经纬度和session
     *
     * @param date
     * @param lon       经度
     * @param lat       纬度
     * @param issession
     */
    public void addLocationAndSession(JSONObject date, boolean lon,
                                      boolean lat, boolean issession, Context context) {
        try {
            SystemInfo systemInfo = SharePrenceUtil.getSystemInfo(context);
            if (lon) {
                date.put("longitude", getLongitude);
                date.put("longitude", 116.419023);
            }
            if (lat) {
                date.put("latitude", getLatitude);
                date.put("latitude", 39.975317);
            }
            if (issession) {
                date.put("sessionid", SharePrenceUtil.getLonginInfo(context)
                        .getSessionId());
            }

            //TODO:
//            date.put("channelid", NetworkConstant.getChannelId(context));
            date.put("channelid", "s10202828225800");
            date.put("devicetoken", "");
            date.put("terminal", systemInfo.getTerminal());
            date.put("sysver", systemInfo.getSystemVersion());
            date.put("appver", systemInfo.getAppVersion());
            date.put("imei", systemInfo.getImei());// systemInfo.getImei()
            date.put("imsi", systemInfo.getImsi());// systemInfo.getImsi()
            date.put("idfa", systemInfo.getIdfa());// systemInfo.getIdfa()
            date.put("android_appver", systemInfo.getA_appver());
            if (BuildConfig.IS_MEIYUAN) {
                date.put("android_meiyuan_appver", systemInfo.getA_appver());
            }
            date.put("macaddr", "");
            date.put("rawmacaddr", systemInfo.getMacaddr());
            date.put("baidu_userid", systemInfo.getBaidu_userid());
            date.put("baidu_channelid", systemInfo.getBaidu_channelid());
            date.put("skeyver", systemInfo.getsKeyVersion());
            date.put("md5", SharePrenceUtil.getLocalMd5(context));
            date.put("md5", "985b1b7e173a6cb671cc31e7afd0e179");
            date.put("gd", systemInfo.getSensor());
            date.put("nt", NetUtil.getNetWorkStateForServer(context));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检测字符串可否转为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
}
