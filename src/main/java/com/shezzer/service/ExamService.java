package com.shezzer.service;

import com.shezzer.pojo.Exam;

import java.util.List;
import java.util.Map;

public interface ExamService {
    boolean addExam(Exam exam);
    boolean updateExam(Exam exam);
    boolean deleteExam(Exam exam);
    Exam checkExam(Exam exam);
    Exam findExamById(int EXAM_ID);
    List<Map> findExamByCourse(int COURSE_ID);
    List<Map> findExamByTeacher(int TEACHER_ID);
    List<Map> findExamByStudent(int STUDENT_ID);
}
