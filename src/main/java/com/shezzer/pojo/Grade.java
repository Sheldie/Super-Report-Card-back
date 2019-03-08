package com.shezzer.pojo;

public class Grade {
    private int GRADE_ID;
    private int EXAM_ID;
    private int STUDENT_ID;
    private int GRADE;
    private int TARGET;

    public Grade() {
    }

    public Grade(int EXAM_ID, int STUDENT_ID) {
        this.EXAM_ID = EXAM_ID;
        this.STUDENT_ID = STUDENT_ID;
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
}
