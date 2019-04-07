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
    public boolean updateTarget(Grade grade) {
        gradeMapper.updateTarget(grade);
        return true;
    }

    @Override
    public Grade findGradeById(int GRADE_ID) {
        return gradeMapper.findGradeById(GRADE_ID);
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
    public Map gradeSegmentation(int EXAM_ID) {
        return gradeMapper.gradeSegmentation(EXAM_ID);
    }

    @Override
    public List<Map> gradeRate(int EXAM_ID) {
        return gradeMapper.gradeRate(EXAM_ID);
    }

    @Override
    public Map getRankByExamAndStudent(int STUDENT_ID, int EXAM_ID) {
        return gradeMapper.getRankByExamAndStudent(STUDENT_ID, EXAM_ID);
    }

    @Override
    public Map<String, String> gradeData(int EXAM_ID) {
        return gradeMapper.gradeData(EXAM_ID);
    }
}
