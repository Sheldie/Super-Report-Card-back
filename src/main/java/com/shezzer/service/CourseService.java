package com.shezzer.service;

import com.shezzer.pojo.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {
    Course findCourseById(int COURSE_ID);
//    Course findCourseByName(String COURSE_NAME);
    Course checkCourse(Course course);
    List<Map> findCourseByTeacher(int TEACHER_ID);
    boolean addCourse(Course course);
}
