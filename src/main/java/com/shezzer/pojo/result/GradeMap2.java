package com.shezzer.pojo.result;

import java.sql.Date;

public class GradeMap2 {
    private int GRADE_ID;
    private String SEMESTER;
    private Date EXAM_DATE;
    private String EXAM_NAME;
    private int GRADE;
    private int TARGET;
    private int T_SCORE;
    private String T_COMMENT;
    private int S_SCORE;
    private String S_COMMENT;

    public int getGRADE_ID() {
        return GRADE_ID;
    }

    public void setGRADE_ID(int GRADE_ID) {
        this.GRADE_ID = GRADE_ID;
    }

    public String getSEMESTER() {
        return SEMESTER;
    }

    public void setSEMESTER(String SEMESTER) {
        this.SEMESTER = SEMESTER;
    }

    public String getEXAM_DATE() {
        return EXAM_DATE.toString();
    }

    public void setEXAM_DATE(Date EXAM_DATE) {
        this.EXAM_DATE = EXAM_DATE;
    }

    public String getEXAM_NAME() {
        return EXAM_NAME;
    }

    public void setEXAM_NAME(String EXAM_NAME) {
        this.EXAM_NAME = EXAM_NAME;
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

    public int getT_SCORE() {
        return T_SCORE;
    }

    public void setT_SCORE(int t_SCORE) {
        T_SCORE = t_SCORE;
    }

    public String getT_COMMENT() {
        return T_COMMENT;
    }

    public void setT_COMMENT(String t_COMMENT) {
        T_COMMENT = t_COMMENT;
    }

    public int getS_SCORE() {
        return S_SCORE;
    }

    public void setS_SCORE(int s_SCORE) {
        S_SCORE = s_SCORE;
    }

    public String getS_COMMENT() {
        return S_COMMENT;
    }

    public void setS_COMMENT(String s_COMMENT) {
        S_COMMENT = s_COMMENT;
    }
}
