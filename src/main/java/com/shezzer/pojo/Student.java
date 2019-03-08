package com.shezzer.pojo;

public class Student {
    private int USER_ID;
    private String STUDENT_NAME;
    private String STUDENT_NUM;
    private int CLASS_ID;

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getSTUDENT_NAME() {
        return STUDENT_NAME;
    }

    public void setSTUDENT_NAME(String STUDENT_NAME) {
        this.STUDENT_NAME = STUDENT_NAME;
    }

    public String getSTUDENT_NUM() {
        return STUDENT_NUM;
    }

    public void setSTUDENT_NUM(String STUDENT_NUM) {
        this.STUDENT_NUM = STUDENT_NUM;
    }

    public int getCLASS_ID() {
        return CLASS_ID;
    }

    public void setCLASS_ID(int CLASS_ID) {
        this.CLASS_ID = CLASS_ID;
    }
}
