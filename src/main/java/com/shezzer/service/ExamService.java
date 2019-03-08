package com.shezzer.service;

import com.shezzer.pojo.Exam;

public interface ExamService {
    boolean addExam(Exam exam);
    Exam checkExam(Exam exam);
    Exam findExamById(int EXAM_ID);
}
