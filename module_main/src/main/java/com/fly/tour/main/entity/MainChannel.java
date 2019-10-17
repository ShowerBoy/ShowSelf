package com.fly.tour.main.entity;

/**
 * Description: <主频道类型><br>
 * Author:      mxdl<br>
 * Date:        2018/12/12<br>
 * Version:     V1.0.0<br>
 * Update:     <br>
 */
public enum MainChannel {
    SQUARE(0,"SQUARE"), RANK(1,"RANK"),TEAM(2,"TEAM"),DISCOVER(3,"DISCOVER"),MY(4,"MY");
    public int id;
    public String name;
    MainChannel(int id, String name){
        this.id = id;
        this.name = name;
    }
}
