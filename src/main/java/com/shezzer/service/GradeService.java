package com.shezzer.service;

import com.shezzer.pojo.Grade;

import java.util.List;
import java.util.Map;

public interface GradeService {
    boolean addGrade(Grade grade);
    Grade findGradeByStudentAndExam(Grade grade);
    List<Grade> findGradeByExam(int EXAM_ID);
    List<Map> findGradeByStudentAndSemester(int STUDENT_ID, String SEMESTER);
    Map<String, String> gradeData(int EXAM_ID);
}
