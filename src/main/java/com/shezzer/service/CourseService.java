package com.shezzer.service;

import com.shezzer.pojo.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {
    Course findCourseById(int COURSE_ID);
//    Course findCourseByName(String COURSE_NAME);
    Course checkCourse(Course course);
    List<Course> findCourseBySubject(int SUBJECT_ID);
    List<Map> getCourseBySubject(int SUBJECT_ID);
    List<Map> findClassByTeacher(int TEACHER_ID);
    List<Map> findCourseByTeacher(int TEACHER_ID);
    List<Map> findCourseByStudent(int STUDENT_ID);
    boolean addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(int COURSE_ID);
}
