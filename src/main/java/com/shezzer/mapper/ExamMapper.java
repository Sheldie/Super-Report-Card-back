package com.shezzer.mapper;

import com.shezzer.pojo.Exam;

public interface ExamMapper {
    void addExam(Exam exam);
    Exam checkExam(Exam exam);
    Exam findExamById(int EXAM_ID);
}
