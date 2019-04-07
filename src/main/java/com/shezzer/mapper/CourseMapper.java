package com.shezzer.mapper;

import com.shezzer.pojo.Course;

import java.util.List;
import java.util.Map;

public interface CourseMapper {
    Course findCourseById(int COURSE_ID);
//    Course findCourseByName(String COURSE_NAME);
    Course checkCourse(Course course);
    List<Map> findCourseByTeacher(int TEACHER_ID);
    List<Map> findCourseByStudent(int STUDENT_ID);
    void addCourse(Course course);
}
