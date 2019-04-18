package com.shezzer.pojo.result;

import java.sql.Date;

public class CommentMap {
    private int COMMENT_ID;
    private int COMMENT_USER;
    private String USER_NAME;
    private int ARTICLE_ID;
    private Date COMMENT_TIME;
    private String COMMENT_TEXT;

    public int getCOMMENT_ID() {
        return COMMENT_ID;
    }

    public void setCOMMENT_ID(int COMMENT_ID) {
        this.COMMENT_ID = COMMENT_ID;
    }

    public int getCOMMENT_USER() {
        return COMMENT_USER;
    }

    public void setCOMMENT_USER(int COMMENT_USER) {
        this.COMMENT_USER = COMMENT_USER;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public int getARTICLE_ID() {
        return ARTICLE_ID;
    }

    public void setARTICLE_ID(int ARTICLE_ID) {
        this.ARTICLE_ID = ARTICLE_ID;
    }

    public String getCOMMENT_TIME() {
        return COMMENT_TIME.toString();
    }

//    public void setCOMMENT_TIME(Date COMMENT_TIME) {
//        this.COMMENT_TIME = COMMENT_TIME;
//    }

    public String getCOMMENT_TEXT() {
        return COMMENT_TEXT;
    }

    public void setCOMMENT_TEXT(String COMMENT_TEXT) {
        this.COMMENT_TEXT = COMMENT_TEXT;
    }
}
