package com.shezzer.service;

import com.shezzer.pojo.Grade;

import java.util.List;
import java.util.Map;

public interface GradeService {
    boolean addGrade(Grade grade);
    boolean updateGrade(Grade grade);
    boolean updateTarget(Grade grade);
    boolean deleteGrade(int GRADE_ID);
    boolean studentComment(Grade grade);
    boolean teacherComment(Grade grade);
    Grade findGradeById(int GRADE_ID);
    Grade findGradeByStudentAndExam(Grade grade);
    List<Map> findGradeByExam(int EXAM_ID);
    List<Map> getGradeByExam(int EXAM_ID);
    List<Map> findGradeByStudentAndSemester(int STUDENT_ID, String SEMESTER);
    List<Map> findGradeByStudentAndCourse(int COURSE_ID, int STUDENT_ID);
    Map gradeSegmentation(int EXAM_ID);
    List<Map> gradeRate(int EXAM_ID);
    Map getRankByExamAndStudent(int STUDENT_ID, int EXAM_ID);
    Map<String, String> gradeData(int EXAM_ID);
}
