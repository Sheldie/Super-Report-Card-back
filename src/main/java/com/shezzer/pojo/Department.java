package com.shezzer.pojo;

public class Department {
    private int DEPARTMENT_ID;
    private String DEPARTMENT_NAME;
    private int SCHOOL_ID;

    public Department(){}

    public Department(String DEPARTMENT_NAME, int SCHOOL_ID) {
        this.DEPARTMENT_NAME = DEPARTMENT_NAME;
        this.SCHOOL_ID = SCHOOL_ID;
    }

    public int getDEPARTMENT_ID() {
        return DEPARTMENT_ID;
    }

    public void setDEPARTMENT_ID(int DEPARTMENT_ID) {
        this.DEPARTMENT_ID = DEPARTMENT_ID;
    }

    public String getDEPARTMENT_NAME() {
        return DEPARTMENT_NAME;
    }

    public void setDEPARTMENT_NAME(String DEPARTMENT_NAME) {
        this.DEPARTMENT_NAME = DEPARTMENT_NAME;
    }

    public int getSCHOOL_ID() {
        return SCHOOL_ID;
    }

    public void setSCHOOL_ID(int SCHOOL_ID) {
        this.SCHOOL_ID = SCHOOL_ID;
    }
}
