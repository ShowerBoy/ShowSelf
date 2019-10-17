package com.fly.tour.api.entity;

import java.io.Serializable;

public class Status implements Serializable {
    public int statuscode;
    public String message;

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
