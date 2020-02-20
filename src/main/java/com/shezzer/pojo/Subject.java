package com.shezzer.pojo;

public class Subject {

    private int SUBJECT_ID;
    private String SUBJECT_NAME;

    public Subject() {
    }

    public Subject(String SUBJECT_NAME) {
        this.SUBJECT_NAME = SUBJECT_NAME;
    }

    public int getSUBJECT_ID() {
        return SUBJECT_ID;
    }

    public void setSUBJECT_ID(int SUBJECT_ID) {
        this.SUBJECT_ID = SUBJECT_ID;
    }

    public String getSUBJECT_NAME() {
        return SUBJECT_NAME;
    }

    public void setSUBJECT_NAME(String SUBJECT_NAME) {
        this.SUBJECT_NAME = SUBJECT_NAME;
    }
}
