package com.shezzer.service.impl;

import com.shezzer.mapper.ExamMapper;
import com.shezzer.pojo.Exam;
import com.shezzer.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
