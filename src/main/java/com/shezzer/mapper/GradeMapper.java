package com.shezzer.mapper;

import com.shezzer.pojo.Grade;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GradeMapper {
    void addGrade(Grade grade);
    Grade findGradeByStudentAndExam(Grade grade);
    List<Map> findGradeByStudentAndSemester(@Param("STUDENT_ID") int STUDENT_ID, @Param("SEMESTER") String SEMESTER);
    List<Grade> findGradeByExam(int EXAM_ID);
    Map<String,String> gradeData(int EXAM_ID);
//    List<Grade> findGradeByStudentAndSemester(@Param("STUDENT_ID") int STUDENT_ID, @Param("SEMESTER") String SEMESTER);
}
