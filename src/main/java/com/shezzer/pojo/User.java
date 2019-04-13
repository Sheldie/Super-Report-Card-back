package com.shezzer.pojo;

import java.sql.Timestamp;
import java.util.Date;

public class User {
    private int USER_ID;
    private String USER_NAME;
    private String USER_PASSWORD;
    private int USER_AUTHORITY;


    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getUSER_PASSWORD() {
        return USER_PASSWORD;
    }

    public void setUSER_PASSWORD(String USER_PASSWORD) {
        this.USER_PASSWORD = USER_PASSWORD;
    }

    public int getUSER_AUTHORITY() {
        return USER_AUTHORITY;
    }

    public void setUSER_AUTHORITY(int USER_AUTHORITY) {
        this.USER_AUTHORITY = USER_AUTHORITY;
    }

}
