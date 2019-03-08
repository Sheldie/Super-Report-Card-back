package com.shezzer.service;

import com.shezzer.pojo.Course;

public interface CourseService {
    Course findCourseById(int COURSE_ID);
    Course findCourseByName(String COURSE_NAME);
    Course checkCourse(Course course);
    boolean addCourse(Course course);
}
