package com.shezzer.pojo.result;

import java.sql.Date;

public class ArticleMap {
    private int ARTICLE_ID;
    private String ARTICLE_TITLE;
    private int ARTICLE_AUTHOR;
    private String TEACHER_NAME;
    private Date ARTICLE_TIME;
    private String ARTICLE_TEXT;

    public ArticleMap(){}

    public int getARTICLE_ID() {
        return ARTICLE_ID;
    }

    public void setARTICLE_ID(int ARTICLE_ID) {
        this.ARTICLE_ID = ARTICLE_ID;
    }

    public String getARTICLE_TITLE() {
        return ARTICLE_TITLE;
    }

    public void setARTICLE_TITLE(String ARTICLE_TITLE) {
        this.ARTICLE_TITLE = ARTICLE_TITLE;
    }

    public int getARTICLE_AUTHOR() {
        return ARTICLE_AUTHOR;
    }

    public void setARTICLE_AUTHOR(int ARTICLE_AUTHOR) {
        this.ARTICLE_AUTHOR = ARTICLE_AUTHOR;
    }

    public String getTEACHER_NAME() {
        return TEACHER_NAME;
    }

    public void setTEACHER_NAME(String TEACHER_NAME) {
        this.TEACHER_NAME = TEACHER_NAME;
    }

    public String getARTICLE_TIME() {
//        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ARTICLE_TIME);
        return ARTICLE_TIME.toString();
    }
//
//    public void setARTICLE_TIME(Date ARTICLE_TIME) {
//        this.ARTICLE_TIME = ARTICLE_TIME;
//    }

    public String getARTICLE_TEXT() {
        return ARTICLE_TEXT;
    }

    public void setARTICLE_TEXT(String ARTICLE_TEXT) {
        this.ARTICLE_TEXT = ARTICLE_TEXT;
    }
}
