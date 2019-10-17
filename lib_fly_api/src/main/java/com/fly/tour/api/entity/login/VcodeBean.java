package com.fly.tour.api.entity.login;

import java.io.Serializable;

/**
 * 获取验证码返回bean
 * Create by wangLei on 2019/10/16
 *
 * "sessionid":null,
 *         "uid":"0",
 *         "username":"",
 *         "gender":0,
 *         "email":"",
 *         "avatar":"",
 *         "tags":"",
 *         "money":0,
 *         "mobile":"13703903621",
 *         "appver":"7.6.3",
 *         "appurl":"http://imeeta/123",
 *         "client_timeout":"90",
 *         "client_retry_times":"1",
 *         "xmpp_client_timeout":"20",
 *         "notifinterval":"90",
 *         "global_pp_status":3,
 *         "global_game_status":1,
 *         "global_vip_status":"1",
 *         "resource_notifinterval":"1800",
 *         "android_notifinterval":"7200",
 *         "v_url":"http://pics.showself.com/upload_pics/uploads_admin/prop/task/reward_ok.png",
 *         "is_new_ios":"1.新增守护进场特效，效果棒棒哒；
 * 2.新增五连砸功能，解放你的手指；
 * 3.优化护卫队功能，宝箱中一键发起集结；
 * 4.优化主播用户升级提醒，快来帮主播接生哦；
 * ",
 *         "is_new_android":"",
 *         "global_gamehall_status":1,
 *         "global_sendvip_status":1,
 *         "is_https":"on",
 *         "ofserver_ip":"192.168.84.68",
 *         "ofserver_port":"5222",
 *         "serv_ipaddr":"192.168.84.129",
 *         "max_voice":300,
 *         "max_video":90,
 *         "shall_other_switch":"1,2,3",
 *         "shall_chests_switch":"1,2,3,4,5,6,7,1125,1127,9,1126,19,10,11,12,14,20,15,21,22,23,24",
 *         "shall_privilege_switch":"7,6,1,8,2,3,5",
 *         "shall_shop_switch":"1,2,3",
 *         "shall_discovery_switch":"1,2,3,4,5,6,7,1509",
 *         "shall_achievement_switch":"1,2,3,4,5"
 */

public class VcodeBean implements Serializable {
    private String sessionid;
    private String uid;
    private String username;
    private String email;
    private String avatar;
    private String tags;
    private String mobile;
    private String appver;
    private String appurl;
    private String client_timeout;
    private String client_retry_times;
    private String xmpp_client_timeout;
    private String notifinterval;
    private String global_vip_status;
    private String resource_notifinterval;
    private String android_notifinterval;
    private String v_url;
    private int gender;
    private int money;
    private int global_pp_status;
    private int global_game_status;
    private int global_gamehall_status;
    private int global_sendvip_status;

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAppver() {
        return appver;
    }

    public void setAppver(String appver) {
        this.appver = appver;
    }

    public String getAppurl() {
        return appurl;
    }

    public void setAppurl(String appurl) {
        this.appurl = appurl;
    }

    public String getClient_timeout() {
        return client_timeout;
    }

    public void setClient_timeout(String client_timeout) {
        this.client_timeout = client_timeout;
    }

    public String getClient_retry_times() {
        return client_retry_times;
    }

    public void setClient_retry_times(String client_retry_times) {
        this.client_retry_times = client_retry_times;
    }

    public String getXmpp_client_timeout() {
        return xmpp_client_timeout;
    }

    public void setXmpp_client_timeout(String xmpp_client_timeout) {
        this.xmpp_client_timeout = xmpp_client_timeout;
    }

    public String getNotifinterval() {
        return notifinterval;
    }

    public void setNotifinterval(String notifinterval) {
        this.notifinterval = notifinterval;
    }

    public String getGlobal_vip_status() {
        return global_vip_status;
    }

    public void setGlobal_vip_status(String global_vip_status) {
        this.global_vip_status = global_vip_status;
    }

    public String getResource_notifinterval() {
        return resource_notifinterval;
    }

    public void setResource_notifinterval(String resource_notifinterval) {
        this.resource_notifinterval = resource_notifinterval;
    }

    public String getAndroid_notifinterval() {
        return android_notifinterval;
    }

    public void setAndroid_notifinterval(String android_notifinterval) {
        this.android_notifinterval = android_notifinterval;
    }

    public String getV_url() {
        return v_url;
    }

    public void setV_url(String v_url) {
        this.v_url = v_url;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getGlobal_pp_status() {
        return global_pp_status;
    }

    public void setGlobal_pp_status(int global_pp_status) {
        this.global_pp_status = global_pp_status;
    }

    public int getGlobal_game_status() {
        return global_game_status;
    }

    public void setGlobal_game_status(int global_game_status) {
        this.global_game_status = global_game_status;
    }

    public int getGlobal_gamehall_status() {
        return global_gamehall_status;
    }

    public void setGlobal_gamehall_status(int global_gamehall_status) {
        this.global_gamehall_status = global_gamehall_status;
    }

    public int getGlobal_sendvip_status() {
        return global_sendvip_status;
    }

    public void setGlobal_sendvip_status(int global_sendvip_status) {
        this.global_sendvip_status = global_sendvip_status;
    }
}
