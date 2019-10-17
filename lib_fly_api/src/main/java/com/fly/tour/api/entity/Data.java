package com.fly.tour.api.entity;

import java.io.Serializable;

/**
 * "longitude":0,
 * "latitude":0,
 * "channelid":"s10202828225800",
 * "devicetoken":"",
 * "terminal":2,
 * "sysver":"7.1.1",
 * "appver":"1.5.0",
 * "imei":"869011021115347",
 * "imsi":"",
 * "idfa":"",
 * "android_appver":"8.2.8",
 * "macaddr":"",
 * "rawmacaddr":"68:3E:34:9E:E8:3A",
 * "baidu_channelid":"120c83f76014d6a90f1",
 * "skeyver":5,
 * "md5":"985b1b7e173a6cb671cc31e7afd0e179",
 * "nt":2
 */

public class Data implements Serializable {
    private int longitude;
    private int latitude;
    private int terminal;
    private int skeyver;
    private int nt;
    private String channelid;
    private String devicetoken;
    private String sysver;
    private String appver;
    private String imei;
    private String imsi;
    private String idfa;
    private String android_appver;
    private String macaddr;
    private String rawmacaddr;
    private String baidu_channelid;
    private String md5;

    private String key;
    private String type;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getTerminal() {
        return terminal;
    }

    public void setTerminal(int terminal) {
        this.terminal = terminal;
    }

    public int getSkeyver() {
        return skeyver;
    }

    public void setSkeyver(int skeyver) {
        this.skeyver = skeyver;
    }

    public int getNt() {
        return nt;
    }

    public void setNt(int nt) {
        this.nt = nt;
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public String getDevicetoken() {
        return devicetoken;
    }

    public void setDevicetoken(String devicetoken) {
        this.devicetoken = devicetoken;
    }

    public String getSysver() {
        return sysver;
    }

    public void setSysver(String sysver) {
        this.sysver = sysver;
    }

    public String getAppver() {
        return appver;
    }

    public void setAppver(String appver) {
        this.appver = appver;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getAndroid_appver() {
        return android_appver;
    }

    public void setAndroid_appver(String android_appver) {
        this.android_appver = android_appver;
    }

    public String getMacaddr() {
        return macaddr;
    }

    public void setMacaddr(String macaddr) {
        this.macaddr = macaddr;
    }

    public String getRawmacaddr() {
        return rawmacaddr;
    }

    public void setRawmacaddr(String rawmacaddr) {
        this.rawmacaddr = rawmacaddr;
    }

    public String getBaidu_channelid() {
        return baidu_channelid;
    }

    public void setBaidu_channelid(String baidu_channelid) {
        this.baidu_channelid = baidu_channelid;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
