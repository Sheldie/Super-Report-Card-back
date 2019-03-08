package com.shezzer.mapper;

import com.shezzer.pojo.Course;

public interface CourseMapper {
    Course findCourseById(int COURSE_ID);
    Course findCourseByName(String COURSE_NAME);
    Course checkCourse(Course course);
    void addCourse(Course course);
}
