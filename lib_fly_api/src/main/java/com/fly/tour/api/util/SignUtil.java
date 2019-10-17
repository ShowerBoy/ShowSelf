package com.fly.tour.api.util;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Created by admin on 2017/5/17.
 */

public class SignUtil {

    /**
     * get请求进行签名
     */
    public static String signGetRequestUrl(String url) {
        return signPostRequestUrl(url, null);
    }

    /**
     * post请求进行签名
     */
    public static String signPostRequestUrl(String url, String jsonStr) {
        String path = getPath(url);
        if (!TextUtils.isEmpty(path)) {
            url = getSignedUrl(url, path, jsonStr);
        }
        return url;
    }

    /**
     * 针对EnBaseHttp和PostParams里的get请求进行签名
     */
    private static String getSignedUrl(String url, String path, String jsonStr) {

        path = "path=" + path;
        String appId = "appId=showself";
        String timeStamp = "timestamp=" + System.currentTimeMillis();

        // get请求：jsonStr = null, paramNum = 3
        String jsonData = "";
        int paramNum = 3;
        boolean isPostRequest = false;
        // get请求：jsonStr != null, paramNum = 4
        if (jsonStr != null) {
            jsonData = "jsonData=" + jsonStr;
            paramNum = 4;
            isPostRequest = true;
        }

        //url中带的参数
        String params = url.substring(url.indexOf("?") + 1, url.length());
        String[] arr;
        if (!TextUtils.isEmpty(params)) {
            String[] arrParams = params.split("&");
            arr = new String[arrParams.length + paramNum];
            arr[0] = appId;
            arr[1] = path;
            arr[2] = timeStamp;
            if (isPostRequest) {
                arr[3] = jsonData;
            }
            System.arraycopy(arrParams, 0, arr, paramNum, arrParams.length);
        } else {
            arr = new String[paramNum];
            arr[0] = appId;
            arr[1] = path;
            arr[2] = timeStamp;
            if (isPostRequest) {
                arr[3] = jsonData;
            }
        }

        //排序
        sort(arr);

        //urlEncode
        for (int i = 0; i < arr.length; i++) {
            String[] arrEncode = arr[i].split("=");
            String encodedValue = toURLEncodedString(arrEncode[1]);
            arr[i] = arrEncode[0] + "=" + encodedValue;
        }

        //重新组合成字符串
        String signStr = "";
        for (int i = 0; i < arr.length; i++) {
            String divider = i == 0 ? "" : "&";
            signStr += divider + arr[i];
        }

        signStr += "123456";

        //生成签名
        String sign = "sign=" + MD5(signStr);

        //生成最终的url
        url += "&" + appId + "&" + sign + "&" + timeStamp;

        return url;
    }

    public static String getSign(String url, String path, String jsonStr) {
        path = "path=" + path;
        String appId = "appId=showself";
        String timeStamp = "timestamp=" + System.currentTimeMillis();

        // get请求：jsonStr = null, paramNum = 3
        String jsonData = "";
        int paramNum = 3;
        boolean isPostRequest = false;
        // get请求：jsonStr != null, paramNum = 4
        if (jsonStr != null) {
            jsonData = "jsonData=" + jsonStr;
            paramNum = 4;
            isPostRequest = true;
        }

        //url中带的参数
        String params = url.substring(url.indexOf("?") + 1, url.length());
        String[] arr;
        if (!TextUtils.isEmpty(params)) {
            String[] arrParams = params.split("&");
            arr = new String[arrParams.length + paramNum];
            arr[0] = appId;
            arr[1] = path;
            arr[2] = timeStamp;
            if (isPostRequest) {
                arr[3] = jsonData;
            }
            System.arraycopy(arrParams, 0, arr, paramNum, arrParams.length);
        } else {
            arr = new String[paramNum];
            arr[0] = appId;
            arr[1] = path;
            arr[2] = timeStamp;
            if (isPostRequest) {
                arr[3] = jsonData;
            }
        }

        //排序
        sort(arr);

        //urlEncode
        for (int i = 0; i < arr.length; i++) {
            String[] arrEncode = arr[i].split("=");
            String encodedValue = toURLEncodedString(arrEncode[1]);
            arr[i] = arrEncode[0] + "=" + encodedValue;
        }

        //重新组合成字符串
        String signStr = "";
        for (int i = 0; i < arr.length; i++) {
            String divider = i == 0 ? "" : "&";
            signStr += divider + arr[i];
        }

        signStr += "123456";

        //生成签名
//        String sign = "sign=" + MD5(signStr);
        return MD5(signStr);
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
     * 排序
     */
    private static void sort(String[] chs) {
        Arrays.sort(chs);
    }

    /**
     * urlEncode
     */
    private static String toURLEncodedString(String paramString) {
        if (TextUtils.isEmpty(paramString)) {
            return "";
        }

        try {
            String str = new String(paramString.getBytes(), "UTF-8");
            paramString = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return paramString;
    }

    public static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
