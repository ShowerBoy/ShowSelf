package com.fly.tour.common.entity;

import android.text.TextUtils;


import com.fly.tour.common.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class LoginResultInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    public LoadingAdBean mLoadingAdBean;
    /**
     * 首页Tab的排列位置
     */
    public int homepage_index;
    /**
     * 秀场Tab的排列位置
     */
    public int liveshow_index;
    /**
     * 消息盒子Tab的排列位置
     */
    public int messagebox_index;
    /**
     * 发现Tab的排列位置
     */
    public int discover_index;
    /**
     * 任务奖励开关
     */
    public int taskDef;
    public int gbswitch;
    /**
     * 房间页海报开关
     */
    public String annoucement; // 入場系統消息
    private String sessionId; // session 用户唯一标识
    private String userName; // 昵称
    private int gender; // 性别
    private int userId; // 用户id
    private int showid; // 广告墙使用的id
    private String email; // 用户邮箱
    private String avatar; // 用户头像
    private int alterpwd; // 判断一键登录用户是否修改过密码 0是修改过 1是未修改
    private int photoNum; // 相册数量
    private String mobile; // 电话号码
    private String accountString;
    private int emailstatus;
    private int mobilestatus;
    private int notificationInterval; // 轮询请求时间间隔
    private int skyVersion;
    private String skey;
    private String appVersion;
    private String appUrl;
    private int xmppPort;
    private String xmppServer;
    private int client_timeout;
    private int client_retry_times;
    private int xmpp_client_timeout;
    private String sinaWeiboId;
    private String qqOpenId;
    private String renrenId;
    private String wxOpenId;
    private int loginType; // 表示登录类型，0表示普通登录，1表示新浪登录，2表示腾讯登录，3表示人人登录,5表示微信登录
    private String accessToken;
    private String intro;
    private String fan_board_def;
    private long cr_hours;//用户注册时间
    private String is_new_android;//新版本更新提示语
    private int global_vip_status; //vip状态
    private int global_game_status; //游戏大厅是否可点击
    private String v_url; //vip图片地址
    private String btn_reward_url; //任务奖励图片地址
    private String btn_task_url; //任务点击按钮地址
    private String bg_task_url; //任务奖励背景地址
    private String task_reward_h5_url; //任务h5地址
    private String task_reward_room_h5_url; //房间任务h5地址
    private int global_pp_status;//

    //青少年模式
    private int spvswitch;
    private int credit_level;

    public static LoginResultInfo jsonToLoginInfo(String loginJsonString) {
        LoginResultInfo loginInfo = null;

        if (loginJsonString != null) {
            loginInfo = new LoginResultInfo();
            JSONObject loginJson;

            try {

                loginJson = new JSONObject(loginJsonString);

                loginInfo.homepage_index = (loginJson.optInt("homepage_index"));
                loginInfo.liveshow_index = (loginJson.optInt("liveshow_index"));

                loginInfo.discover_index = (loginJson.optInt("discover_index"));
                loginInfo.messagebox_index = (loginJson
                        .optInt("messagebox_index"));

                loginInfo.gbswitch = loginJson.optInt("gbswitch");
                if (!loginJson.isNull("sessionid")) {
                    loginInfo.setSessionId(loginJson.optString("sessionid"));
                }
                if (!loginJson.isNull("msisdn")) {
                    String oneKeyMobile = loginJson.optString("msisdn");
                    if (!TextUtils.isEmpty(oneKeyMobile)) {
                        PreferenceOfLoginInfo.getInstance().updateOneKeyLoginInfo(oneKeyMobile);
                    }
                }

                if (!loginJson.isNull("uid")) {
                    loginInfo.setUserId(Integer.parseInt(loginJson
                            .optString("uid")));
                }
                if (loginJson.optJSONArray("annoucement") != null) {
                    loginInfo.annoucement = loginJson.optJSONArray("annoucement").toString();
                }

                loginInfo.setIntro(loginJson.optString("intro"));
                loginInfo.setShowid(loginJson.optInt("showid"));
                if (!loginJson.isNull("username")) {
                    loginInfo.setUserName(loginJson.optString("username"));
                }
                if (!loginJson.isNull("email")) {
                    loginInfo.setEmail(loginJson.optString("email"));
                }
                if (!loginJson.isNull("avatar")) {
                    loginInfo.setAvatar(loginJson.optString("avatar"));
                }
                if (!loginJson.isNull("gender")) {
                    loginInfo.setGender(Integer.parseInt(loginJson
                            .optString("gender")));
                }
                if (!loginJson.isNull("alterpwd")) {
                    loginInfo.setAlterpwd(Integer.parseInt(loginJson
                            .optString("alterpwd")));
                }
                if (!loginJson.isNull("mobile")) {
                    loginInfo.setMobile(loginJson.optString("mobile"));
                }
                if (!loginJson.isNull("mobilestatus")) {
                    loginInfo.setMobilestatus(Integer.parseInt(loginJson
                            .optString("mobilestatus")));
                }
                if (!loginJson.isNull("emailstatus")) {
                    loginInfo.setEmailstatus(Integer.parseInt(loginJson
                            .optString("emailstatus")));
                }
                if (!loginJson.isNull("notifinterval")) {
                    loginInfo.setNotificationInterval(Integer
                            .parseInt(loginJson.optString("notifinterval")));
                }
                if (!loginJson.isNull("ofserver_port")) {
                    loginInfo.setXmppPort(Integer.parseInt(loginJson
                            .optString("ofserver_port")));
                }
                if (!loginJson.isNull("ofserver_ip")) {
                    loginInfo.setXmppServer(loginJson.optString("ofserver_ip"));
                }
                if (!loginJson.isNull("skeyver")
                        && CommonUtils.isNumeric(loginJson.optString("skeyver"))) {
                    loginInfo.skyVersion = Integer.parseInt(loginJson
                            .optString("skeyver"));
                }
                if (!loginJson.isNull("skey")) {
                    loginInfo.setSkey(loginJson.optString("skey"));
                }
                if (!loginJson.isNull("appver")) {
                    loginInfo.setAppVersion(loginJson.optString("appver"));
                }
                if (!loginJson.isNull("appurl")) {
                    loginInfo.setAppUrl(loginJson.optString("appurl"));
                }
                if (!loginJson.isNull("client_timeout")) {
                    loginInfo.setClient_timeout(Integer.parseInt(loginJson
                            .optString("client_timeout")));
                }
                if (!loginJson.isNull("client_retry_times")) {
                    loginInfo.setClient_retry_times(Integer.parseInt(loginJson
                            .optString("client_retry_times")));
                }
                if (!loginJson.isNull("xmpp_client_timeout")) {
                    loginInfo.setXmpp_client_timeout(Integer.parseInt(loginJson
                            .optString("xmpp_client_timeout")));
                }
                if (!loginJson.isNull("taskDef")) {
                    loginInfo.setTaskDef(loginJson.optInt("taskDef"));
                }
                if (!loginJson.isNull("cr_hours")) {
                    loginInfo.setCr_hours(loginJson.optLong("cr_hours"));
                }
                if (!loginJson.isNull("fan_board_def")) {
                    loginInfo.setFansListArray(loginJson.optJSONArray("fan_board_def").toString());
                }
                if (!loginJson.isNull("is_new_android")) {
                    loginInfo.setIs_new_android(loginJson.optString("is_new_android"));
                }
                if (!loginJson.isNull("is_new_android")) {
                    loginInfo.setIs_new_android(loginJson.optString("is_new_android"));
                }
                if (!loginJson.isNull("global_game_status")) {
                    loginInfo.setGlobal_game_status(loginJson.optInt("global_game_status"));
                }
                if (!loginJson.isNull("global_vip_status")) {
                    loginInfo.setGlobal_vip_status(loginJson.optInt("global_vip_status"));
                }
                if (!loginJson.isNull("v_url")) {
                    loginInfo.setV_url(loginJson.optString("v_url"));
                }
                if (!loginJson.isNull("btn_task_url")) {
                    loginInfo.setBtnTaskUrl(loginJson.optString("btn_task_url"));
                }
                if (!loginJson.isNull("bg_task_url")) {
                    loginInfo.setBgTaskUrl(loginJson.optString("bg_task_url"));
                }
                if (!loginJson.isNull("btn_reward_url")) {
                    loginInfo.setBtnRewardUrl(loginJson.optString("btn_reward_url"));
                }
                if (!loginJson.isNull("h5_task_addr_url_post")) {
                    loginInfo.setH5RewardUrl(loginJson.optString("h5_task_addr_url_post"));
                }
                if (!loginJson.isNull("h5_task_addr_url_room")) {
                    loginInfo.setH5RoomRewardUrl(loginJson.optString("h5_task_addr_url_room"));
                }
                if (!loginJson.isNull("global_pp_status")) {
                    loginInfo.setGlobal_pp_status(loginJson.optInt("global_pp_status"));
                }
                if (!loginJson.isNull("loading")) {
                    JSONObject loadingObj = loginJson.optJSONObject("loading");
                    LoadingAdBean adBean = new LoadingAdBean();
                    adBean.image = loadingObj.optString("image");
                    adBean.ref_html = loadingObj.optString("ref_html");
                    adBean.title = loadingObj.optString("title");
                    adBean.interv = loadingObj.optInt("interv");
                    loginInfo.mLoadingAdBean = adBean;
                    adBean = null;
                }

                //青少年模式
                loginInfo.setSpvswitch(loginJson.optInt("spvswitch"));
                loginInfo.setCredit_level(loginJson.optInt("credit_level"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return loginInfo;
    }

    public int getGlobal_pp_status() {
        return global_pp_status;
    }

    public void setGlobal_pp_status(int global_pp_status) {
        this.global_pp_status = global_pp_status;
    }

    public int getXmpp_client_timeout() {
        return xmpp_client_timeout;
    }

    public void setXmpp_client_timeout(int xmpp_client_timeout) {
        this.xmpp_client_timeout = xmpp_client_timeout;
    }

    public String getV_url() {
        return v_url;
    }

    public void setV_url(String v_url) {
        this.v_url = v_url;
    }

    public String getBtnTaskUrl() {
        return btn_task_url;
    }

    public void setBtnTaskUrl(String btn_task_url) {
        this.btn_task_url = btn_task_url;
    }

    public String getBgTaskUrl() {
        return bg_task_url;
    }

    public void setBgTaskUrl(String bg_task_url) {
        this.bg_task_url = bg_task_url;
    }

    public String getBtnRewardUrl() {
        return btn_reward_url;
    }

    public void setBtnRewardUrl(String btn_reward_url) {
        this.btn_reward_url = btn_reward_url;
    }

    public String getH5RewardUrl() {
        return task_reward_h5_url;
    }

    public void setH5RewardUrl(String task_reward_h5_url) {
        this.task_reward_h5_url = task_reward_h5_url;
    }

    public String getH5RoomRewardUrl() {
        return task_reward_room_h5_url;
    }

    public void setH5RoomRewardUrl(String task_reward_room_h5_url) {
        this.task_reward_room_h5_url = task_reward_room_h5_url;
    }

    public int getGlobal_vip_status() {
        return global_vip_status;
    }

    public void setGlobal_vip_status(int global_vip_status) {
        this.global_vip_status = global_vip_status;
    }

    public int getGlobal_game_status() {
        return global_game_status;
    }

    public void setGlobal_game_status(int global_game_status) {
        this.global_game_status = global_game_status;
    }

    public String getIs_new_android() {
        return is_new_android;
    }

    public void setIs_new_android(String is_new_android) {
        this.is_new_android = is_new_android;
    }

    public int getTaskDef() {
        return taskDef;
    }

    public void setTaskDef(int taskDef) {
        this.taskDef = taskDef;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getAlterpwd() {
        return alterpwd;
    }

    public void setAlterpwd(int alterpwd) {
        this.alterpwd = alterpwd;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public int getPhotoNum() {
        return photoNum;
    }

    public void setPhotoNum(int photoNum) {
        this.photoNum = photoNum;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAccountString() {
        return accountString;
    }

    public void setAccountString(String accountString) {
        this.accountString = accountString;
    }

    public int getEmailstatus() {
        return emailstatus;
    }

    public void setEmailstatus(int emailstatus) {
        this.emailstatus = emailstatus;
    }

    public int getMobilestatus() {
        return mobilestatus;
    }

    public void setMobilestatus(int mobilestatus) {
        this.mobilestatus = mobilestatus;
    }

    public int getNotificationInterval() {
        return notificationInterval;
    }

    public void setNotificationInterval(int notificationInterval) {
        this.notificationInterval = notificationInterval;
    }

    public int getSkyVersion() {
        return skyVersion;
    }

    public void setSkyVersion(int skyVersion) {
        this.skyVersion = skyVersion;
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public int getXmppPort() {
        return xmppPort;
    }

    public void setXmppPort(int xmppPort) {
        this.xmppPort = xmppPort;
    }

    public String getXmppServer() {
        return xmppServer;
    }

    public void setXmppServer(String xmppServer) {
        this.xmppServer = xmppServer;
    }

    public String getSinaWeiboId() {
        return sinaWeiboId;
    }

    public void setSinaWeiboId(String sinaWeiboId) {
        this.sinaWeiboId = sinaWeiboId;
    }

    public String getQqOpenId() {
        return qqOpenId;
    }

    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }

    public String getRenrenId() {
        return renrenId;
    }

    public void setRenrenId(String renrenId) {
        this.renrenId = renrenId;
    }

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getClient_timeout() {
        return client_timeout;
    }

    public void setClient_timeout(int clientTimeout) {
        client_timeout = clientTimeout;
    }

    public int getClient_retry_times() {
        return client_retry_times;
    }

    public void setClient_retry_times(int clientRetryTimes) {
        client_retry_times = clientRetryTimes;
    }

    public int getShowid() {
        return showid;
    }

    public void setShowid(int showid) {
        this.showid = showid;
    }

    public String getFansListArray() {
        return fan_board_def;
    }

    public void setFansListArray(String fan_board_def) {
        this.fan_board_def = fan_board_def;
    }

    public long getCr_hours() {
        return cr_hours;
    }

    public void setCr_hours(long cr_hours) {
        this.cr_hours = cr_hours;
    }

    public int getSpvswitch() {
        return spvswitch;
    }

    public void setSpvswitch(int spvswitch) {
        this.spvswitch = spvswitch;
    }

    public int getCredit_level() {
        return credit_level;
    }

    public void setCredit_level(int credit_level) {
        this.credit_level = credit_level;
    }
}
