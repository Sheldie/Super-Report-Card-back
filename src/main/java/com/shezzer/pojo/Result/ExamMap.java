package com.shezzer.pojo.result;

import java.sql.Date;

public class ExamMap {
    private int EXAM_ID;
    private String EXAM_NAME;
    private int COURSE_ID;
    private String CLASS_NAME;
    private int GRADE;
    private int TARGET;
    private int GRADE_ID;
    private Date EXAM_DATE;
    private int DAYS_LEFT;
    private int SEAT;

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

    public int getGRADE_ID() {
        return GRADE_ID;
    }

    public void setGRADE_ID(int GRADE_ID) {
        this.GRADE_ID = GRADE_ID;
    }

    public int getGRADE() {
        return GRADE;
    }

    public void setGRADE(int GRADE) {
        this.GRADE = GRADE;
    }

    public int getTARGET() {
        return TARGET;
    }

    public void setTARGET(int TARGET) {
        this.TARGET = TARGET;
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

    public String getCLASS_NAME() {
        return CLASS_NAME;
    }

    public void setCLASS_NAME(String CLASS_NAME) {
        this.CLASS_NAME = CLASS_NAME;
    }

    public int getSEAT() {
        return SEAT;
    }

    public void setSEAT(int SEAT) {
        this.SEAT = SEAT;
    }
}
