package com.fly.tour.api.config;

import com.fly.tour.api.BuildConfig;

/**
 * Description: <API><br>
 * Author:      mxdl<br>
 * Date:        2019/6/23<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public class API {
    private static final String serverType = BuildConfig.SERVER_TYPE;
    public static final String URL_HOST_USER = "http://192.168.31.105:8762";
    public static final String URL_HOST_NEWS = "http://192.168.31.105:8767";

    public static final String BASEURL;
    public static final String TESTURL = "https://testapici.anniekids.com/Api/v2/";

    static {
        if (serverType.equals("local")) {
            BASEURL = "https://192.168.84.129/inf/";
//            currentHostUrlToJava = "http://192.168.84.111";
        } else if (serverType.equals("remoteTest")) {
            BASEURL = "https://e1test.imeeta.com/inf/";
//            currentHostUrlToJava = "https://cstest.imeeta.com";
        } else {
            BASEURL = "https://e1.imeeta.com/inf/";
//            currentHostUrlToJava = "https://cs.imeeta.com";
        }
    }


}
