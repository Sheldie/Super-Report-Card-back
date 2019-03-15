package com.shezzer.service;

import com.shezzer.pojo.Grade;

import java.util.List;
import java.util.Map;

public interface GradeService {
    boolean addGrade(Grade grade);
    boolean updateGrade(Grade grade);
    Grade findGradeByStudentAndExam(Grade grade);
    List<Map> findGradeByExam(int EXAM_ID);
    List<Map> getGradeByExam(int EXAM_ID);
    List<Map> findGradeByStudentAndSemester(int STUDENT_ID, String SEMESTER);
    List<Map> gradeSegmentation(int EXAM_ID);
    Map<String, String> gradeData(int EXAM_ID);
}
