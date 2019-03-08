package com.shezzer.mapper;

import com.shezzer.pojo.Exam;

import java.util.List;

public interface ExamMapper {
    void addExam(Exam exam);
    Exam checkExam(Exam exam);
    Exam findExamById(int EXAM_ID);
    List<Exam> findExamByCourse(int COURSE_ID);
}
