package com.shezzer.mapper;

import com.shezzer.pojo.Grade;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GradeMapper {
    void addGrade(Grade grade);
    void updateGrade(Grade grade);
    void updateTarget(Grade grade);
    void deleteGrade(int GRADE_ID);
    void studentComment(Grade grade);
    void teacherComment(Grade grade);
    void updateSeat(Grade grade);
    Grade findGradeById(int GRADE_ID);
    Grade findGradeByStudentAndExam(Grade grade);
    List<Grade> findGradeByStudent(int STUDENT_ID);
    List<Map> findGradeByStudentAndSemester(@Param("STUDENT_ID") int STUDENT_ID, @Param("SEMESTER") String SEMESTER);
    List<Map> findGradeByExam(int EXAM_ID);
    Map<String,String> gradeData(int EXAM_ID);
    List<Map> getGradeByExam(int EXAM_ID);
    Map gradeSegmentation(int EXAM_ID);
    List<Map> gradeRate(int EXAM_ID);
    List<Map> findGradeByStudentAndCourse(@Param("COURSE_ID")int COURSE_ID, @Param("STUDENT_ID") int STUDENT_ID);
    Map getRankByExamAndStudent(@Param("STUDENT_ID") int STUDENT_ID, @Param("EXAM_ID") int EXAM_ID);
    List<Map> getSeatByExam(int EXAM_ID);
    List<Grade> findSeatByExam(int EXAM_ID);
//    List<Grade> findGradeByStudentAndSemester(@Param("STUDENT_ID") int STUDENT_ID, @Param("SEMESTER") String SEMESTER);
}
