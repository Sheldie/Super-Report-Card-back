package com.shezzer.service.impl;

import com.shezzer.mapper.GradeMapper;
import com.shezzer.pojo.Grade;
import com.shezzer.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    GradeMapper gradeMapper;


    @Override
    public boolean addGrade(Grade grade) {
        gradeMapper.addGrade(grade);
        return true;
    }

    @Override
    public boolean updateGrade(Grade grade) {
        gradeMapper.updateGrade(grade);
        return true;
    }

    @Override
    public Grade findGradeByStudentAndExam(Grade grade) {
        return gradeMapper.findGradeByStudentAndExam(grade);
    }

    @Override
    public List<Map> findGradeByExam(int EXAM_ID) {
        return gradeMapper.findGradeByExam(EXAM_ID);
    }

    @Override
    public List<Map> getGradeByExam(int EXAM_ID) {
        return gradeMapper.getGradeByExam(EXAM_ID);
    }

    @Override
    public List<Map> findGradeByStudentAndSemester(int STUDENT_ID, String SEMESTER) {
        return gradeMapper.findGradeByStudentAndSemester(STUDENT_ID, SEMESTER);
    }

    @Override
    public Map<String, String> gradeData(int EXAM_ID) {
        return gradeMapper.gradeData(EXAM_ID);
    }
}
