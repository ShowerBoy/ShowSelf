package com.showself.utils;

import com.fly.tour.api.BuildConfig;

public class Utils {

    public Utils() {
    }

    static {
        System.loadLibrary("aes");
    }

    private native static String getKey();

    public static String getAesKey() {
        if (BuildConfig.SERVER_TYPE.equals("local")) {
            return "12345678";
        } else {
            return getKey();
        }
    }
}
