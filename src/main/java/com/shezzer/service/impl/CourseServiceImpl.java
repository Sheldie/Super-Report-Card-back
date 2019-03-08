package com.shezzer.service.impl;

import com.shezzer.mapper.CourseMapper;
import com.shezzer.pojo.Course;
import com.shezzer.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public Course findCourseById(int COURSE_ID) {
        return courseMapper.findCourseById(COURSE_ID);
    }

    @Override
    public Course findCourseByName(String COURSE_NAME) {
        return courseMapper.findCourseByName(COURSE_NAME);
    }

    @Override
    public Course checkCourse(Course course) {
        return courseMapper.checkCourse(course);
    }

    @Override
    public boolean addCourse(Course course) {
        courseMapper.addCourse(course);
        return true;
    }
}