package com.shezzer.service;

import com.shezzer.pojo.Exam;

import java.util.List;
import java.util.Map;

public interface ExamService {
    boolean addExam(Exam exam);
    Exam checkExam(Exam exam);
    Exam findExamById(int EXAM_ID);
    List<Exam> findExamByCourse(int COURSE_ID);
}
