package com.fly.tour.common.util.appUtils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.text.TextUtils;

import com.fly.tour.common.BaseApplication;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Random;

public class Utils {
    public Utils() {
    }

    public static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    public static String getOrMakeIMEI() {
        try {
            ContentResolver resolver = BaseApplication.getInstance()
                    .getApplicationContext().getContentResolver();
            String idfa = Settings.System.getString(resolver, "com.sf.im");
            if (!TextUtils.isEmpty(idfa)) {
                return idfa;
            } else {
                idfa = SharePrenceUtil.getIDFA();
                if (!TextUtils.isEmpty(idfa)) {
                    Settings.System.putString(resolver, "com.sf.im", idfa);
                    return idfa;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("s");
                    Random random = new Random();
                    for (int i = 0; i < 14; i++) {
                        sb.append(random.nextInt(9));
                    }
                    idfa = sb.toString();
                    Settings.System.putString(resolver, "com.sf.im", idfa);
                    SharePrenceUtil.saveIDFA(idfa);
                    return idfa;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getPubIdfa() {
        try {
            ContentResolver resolver = BaseApplication.getInstance()
                    .getApplicationContext().getContentResolver();
            String idfa = Settings.System.getString(resolver, "com.test.im");
            if (!TextUtils.isEmpty(idfa)) {
                return idfa;
            } else {
                idfa = SharePrenceUtil.getPUBIDFA();
                if (!TextUtils.isEmpty(idfa)) {
                    Settings.System.putString(resolver, "com.test.im", idfa);
                    return idfa;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("s");
                    Random random = new Random();
                    for (int i = 0; i < 14; i++) {
                        sb.append(random.nextInt(9));
                    }
                    idfa = sb.toString();
                    Settings.System.putString(resolver, "com.test.im", idfa);
                    SharePrenceUtil.savePUBIDFA(idfa);
                    return idfa;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取mac地址
     */
    public static String getMac(Context context) {

        String macAddress = null;
        StringBuffer buf = new StringBuffer();
        NetworkInterface networkInterface = null;
        try {
            networkInterface = NetworkInterface.getByName("eth1");
            if (networkInterface == null) {
                networkInterface = NetworkInterface.getByName("wlan0");
            }
            if (networkInterface == null) {
                return "02:00:00:00:00:02";
            }
            byte[] addr = networkInterface.getHardwareAddress();
            for (byte b : addr) {
                buf.append(String.format("%02X:", b));
            }
            if (buf.length() > 0) {
                buf.deleteCharAt(buf.length() - 1);
            }
            macAddress = buf.toString();
        } catch (SocketException e) {
            e.printStackTrace();
            return "02:00:00:00:00:02";
        }
        return macAddress;
    }
}
