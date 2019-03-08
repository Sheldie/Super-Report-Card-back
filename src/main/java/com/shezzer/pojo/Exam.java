package com.shezzer.pojo;

import java.sql.Date;

public class Exam {
    private int EXAM_ID;
    private String EXAM_NAME;
    private int COURSE_ID;
    private Date EXAM_DATE;
    private int DAYS_LEFT;

    public Exam() {
    }

    public Exam(String EXAM_NAME, int COURSE_ID, Date EXAM_DATE) {
        this.EXAM_NAME = EXAM_NAME;
        this.COURSE_ID = COURSE_ID;
        this.EXAM_DATE = EXAM_DATE;
    }

    public int getEXAM_ID() {
        return EXAM_ID;
    }

    public void setEXAM_ID(int EXAM_ID) {
        this.EXAM_ID = EXAM_ID;
    }

    public String getEXAM_NAME() {
        return EXAM_NAME;
    }

    public void setEXAM_NAME(String EXAM_NAME) {
        this.EXAM_NAME = EXAM_NAME;
    }

    public int getCOURSE_ID() {
        return COURSE_ID;
    }

    public void setCOURSE_ID(int COURSE_ID) {
        this.COURSE_ID = COURSE_ID;
    }

    public String getEXAM_DATE() {
        return EXAM_DATE.toString();
    }

    public void setEXAM_DATE(Date EXAM_DATE) {
        this.EXAM_DATE = EXAM_DATE;
    }

    public int getDAYS_LEFT() {
        return DAYS_LEFT;
    }

}
