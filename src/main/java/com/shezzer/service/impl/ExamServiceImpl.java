package com.shezzer.service.impl;

import com.shezzer.mapper.ExamMapper;
import com.shezzer.pojo.Exam;
import com.shezzer.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    ExamMapper examMapper;

    @Override
    public boolean addExam(Exam exam) {
        examMapper.addExam(exam);
        return true;
    }

    @Override
    public Exam checkExam(Exam exam) {
        return examMapper.checkExam(exam);
    }

    @Override
    public Exam findExamById(int EXAM_ID) {
        return examMapper.findExamById(EXAM_ID);
    }

    @Override
    public List<Exam> findExamByCourse(int COURSE_ID) {
        return examMapper.findExamByCourse(COURSE_ID);
    }
}
