package com.fly.tour.common.util;

import com.fly.tour.common.BaseApplication;
import com.showself.module.common.R;

import java.util.ArrayList;
import java.util.List;


public class PersonalDataUtils {

    public static int TOAST_SHOW_TIME = 1000;
    public static String MALE_TAG = "♂";
    public static String FEMALE_TAG = "♀";
    public static int MALE_VALUE = 1;
    public static int FEMALE_VALUE = 2;
    /**
     * 维度
     */
    public static double getLatitude = 0;
    /**
     * 经度
     */
    public static double getLongitude = 0;
    /**
     * 城市
     */
    public static String getCity = "";
    public static String APPSTORE_URL = "https://download.showself.com/down+.html"; // app下载地址
    public static int VIP_PROMPT_TYPE_SMALL_PHOTO = 115;
    /**
     * VIP提示文字-按钮上面文字
     */
    public static String BOTTON_TEXT = BaseApplication.getInstance().getString(R.string.member_character);

    /**
     * 修改个人资料中获取婚恋状态情况
     */
    public static List<String> getMarryInfo() {
        List<String> infos = new ArrayList<String>();
        infos.add(BaseApplication.getInstance().getResources().getString(R.string.user_singleing));
        infos.add(BaseApplication.getInstance().getResources().getString(R.string.user_loveing));
        infos.add(BaseApplication.getInstance().getResources().getString(R.string.user_married));
        infos.add(BaseApplication.getInstance().getResources().getString(R.string.user_divorced));
        infos.add(BaseApplication.getInstance().getResources().getString(R.string.user_unknown));
        return infos;
    }

    /**
     * 修改个人资料中获取身高的选择值
     */
    public static List<String> getHeightInfo() {
        List<String> infos = new ArrayList<String>();
        for (int i = 0; i < 51; i++) {
            infos.add((150 + i) + "cm");
        }
        return infos;
    }

    /**
     * 修改个人资料中获取体重的选择值
     */
    public static List<String> getWeightInfo() {
        List<String> infos = new ArrayList<String>();
        for (int i = 0; i < 51; i++) {
            infos.add((30 + i) + "kg");
        }
        return infos;
    }

    /**
     * 修改个人资料中获取血型的选择值
     */
    public static List<String> getBloodInfo() {
        List<String> infos = new ArrayList<String>();
        infos.add("A");
        infos.add("B");
        infos.add("O");
        infos.add("AB");
        infos.add(BaseApplication.getInstance().getResources().getString(R.string.rest));
        return infos;
    }

    /**
     * 修改个人资料中获取月薪的选择值
     */
    public static List<String> getMoneyInfo() {
        List<String> infos = new ArrayList<String>();
        infos.add(BaseApplication.getInstance().getResources().getString(R.string.two_thousand_below));
        infos.add("2000-5000");
        infos.add("5000-10000");
        infos.add("10000-20000");
        infos.add(BaseApplication.getInstance().getResources().getString(R.string.twenty_thousand_above));
        return infos;
    }

    /**
     * 修改个人资料中获取胸围的选择值
     */
    public static List<String> getXiongInfo() {
        List<String> infos = new ArrayList<String>();
        for (int i = 0; i < 41; i++) {
            infos.add((70 + i) + "cm");
        }
        return infos;
    }

    /**
     * 修改个人资料中获取腰围的选择值
     */
    public static List<String> getYaoInfo() {
        List<String> infos = new ArrayList<String>();
        for (int i = 0; i < 31; i++) {
            infos.add((50 + i) + "cm");
        }
        return infos;
    }

    /**
     * 修改个人资料中获取臀围的选择值
     */
    public static List<String> getTunInfo() {
        List<String> infos = new ArrayList<String>();
        for (int i = 0; i < 41; i++) {
            infos.add((70 + i) + "cm");
        }
        return infos;
    }

    /**
     * 修改个人资料中获取罩杯的选择值
     */
    public static List<String> getZaoInfo() {
        List<String> infos = new ArrayList<String>();
        infos.add("A");
        infos.add("B");
        infos.add("C");
        infos.add("D");
        infos.add("E");
        infos.add("F");
        infos.add("G");
        infos.add("H");
        infos.add("I");
        return infos;
    }
    //===================================================================================
}
