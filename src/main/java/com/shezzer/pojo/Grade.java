package com.shezzer.pojo;

public class Grade {
    private int GRADE_ID;
    private int EXAM_ID;
    private int STUDENT_ID;
    private int GRADE;
    private int TARGET;
    private int T_SCORE;
    private int S_SCORE;
    private String T_COMMENT;
    private String S_COMMENT;
    private int SEAT;

    public Grade() {
    }

    public Grade(int EXAM_ID, int STUDENT_ID, int SEAT) {
        this.EXAM_ID = EXAM_ID;
        this.STUDENT_ID = STUDENT_ID;
        this.SEAT = SEAT;
    }

    public int getGRADE_ID() {
        return GRADE_ID;
    }

    public void setGRADE_ID(int GRADE_ID) {
        this.GRADE_ID = GRADE_ID;
    }

    public int getEXAM_ID() {
        return EXAM_ID;
    }

    public void setEXAM_ID(int EXAM_ID) {
        this.EXAM_ID = EXAM_ID;
    }

    public int getSTUDENT_ID() {
        return STUDENT_ID;
    }

    public void setSTUDENT_ID(int STUDENT_ID) {
        this.STUDENT_ID = STUDENT_ID;
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

    public int getS_SCORE() {
        return S_SCORE;
    }

    public void setS_SCORE(int s_SCORE) {
        S_SCORE = s_SCORE;
    }

    public String getT_COMMENT() {
        return T_COMMENT;
    }

    public void setT_COMMENT(String t_COMMENT) {
        T_COMMENT = t_COMMENT;
    }

    public String getS_COMMENT() {
        return S_COMMENT;
    }

    public void setS_COMMENT(String s_COMMENT) {
        S_COMMENT = s_COMMENT;
    }

    public int getSEAT() {
        return SEAT;
    }

    public void setSEAT(int SEAT) {
        this.SEAT = SEAT;
    }
}
