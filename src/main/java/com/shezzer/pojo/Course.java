package com.shezzer.pojo;

public class Course {
    private int COURSE_ID;
    private int SUBJECT_ID;
    private int TEACHER_ID;
    private int CLASS_ID;
    private String SEMESTER;

    public Course() {
    }

    public Course(int SUBJECT_ID, int TEACHER_ID, int CLASS_ID, String SEMESTER) {
        this.SUBJECT_ID = SUBJECT_ID;
        this.TEACHER_ID = TEACHER_ID;
        this.CLASS_ID = CLASS_ID;
        this.SEMESTER = SEMESTER;
    }

    public int getCOURSE_ID() {
        return COURSE_ID;
    }

    public void setCOURSE_ID(int COURSE_ID) {
        this.COURSE_ID = COURSE_ID;
    }

    public int getSUBJECT_ID() {
        return SUBJECT_ID;
    }

    public void setSUBJECT_ID(int SUBJECT_ID) {
        this.SUBJECT_ID = SUBJECT_ID;
    }

    public int getTEACHER_ID() {
        return TEACHER_ID;
    }

    public void setTEACHER_ID(int TEACHER_ID) {
        this.TEACHER_ID = TEACHER_ID;
    }

    public int getCLASS_ID() {
        return CLASS_ID;
    }

    public void setCLASS_ID(int CLASS_ID) {
        this.CLASS_ID = CLASS_ID;
    }

    public String getSEMESTER() {
        return SEMESTER;
    }

    public void setSEMESTER(String SEMESTER) {
        this.SEMESTER = SEMESTER;
    }

}
