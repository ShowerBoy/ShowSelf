package com.fly.tour.common.util.appUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by admin on 2017/4/24.
 */

public class NetUtil {
    /**
     * 没有连接网络
     */
    public static final int NETWORK_NONE = -1;
    /**
     * 移动网络
     */
    public static final int NETWORK_MOBILE = 0;
    /**
     * 无线网络
     */
    public static final int NETWORK_WIFI = 1;

    public static int getNetWorkState(Context context) {
        // 得到连接管理器对象
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {

            if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_WIFI)) {
                return NETWORK_WIFI;
            } else if (activeNetworkInfo.getType() == (ConnectivityManager.TYPE_MOBILE)) {
                return NETWORK_MOBILE;
            }
        } else {
            return NETWORK_NONE;
        }
        return NETWORK_NONE;
    }

    // 上报app联网状态
    public static int getNetWorkStateForServer(Context context) {
        int netState = -1;
        int netType = NetUtil.getNetWorkState(context);
        if (netType == ConnectivityManager.TYPE_WIFI) {  //WIFI
            netState = 2;
        } else if (netType == ConnectivityManager.TYPE_MOBILE) { //4G
            netState = 1;
        }
        return netState;
    }
}
