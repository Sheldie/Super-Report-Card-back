package com.shezzer.service.impl;

import com.shezzer.mapper.CourseMapper;
import com.shezzer.pojo.Course;
import com.shezzer.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public Course findCourseById(int COURSE_ID) {
        return courseMapper.findCourseById(COURSE_ID);
    }

//    @Override
//    public Course findCourseByName(String COURSE_NAME) {
//        return courseMapper.findCourseByName(COURSE_NAME);
//    }

    @Override
    public Course checkCourse(Course course) {
        return courseMapper.checkCourse(course);
    }

    @Override
    public List<Course> findCourseBySubject(int SUBJECT_ID) {
        return courseMapper.findCourseBySubject(SUBJECT_ID);
    }

    @Override
    public List<Map> getCourseBySubject(int SUBJECT_ID) {
        return courseMapper.getCourseBySubject(SUBJECT_ID);
    }

    @Override
    public List<Map> findClassByTeacher(int TEACHER_ID) {
        return courseMapper.findClassByTeacher(TEACHER_ID);
    }

    @Override
    public List<Map> findCourseByTeacher(int TEACHER_ID) {
        return courseMapper.findCourseByTeacher(TEACHER_ID);
    }

    @Override
    public List<Map> findCourseByStudent(int STUDENT_ID) {
        return courseMapper.findCourseByStudent(STUDENT_ID);
    }

    @Override
    public boolean addCourse(Course course) {
        courseMapper.addCourse(course);
        return true;
    }

    @Override
    public void updateCourse(Course course) {
        courseMapper.updateCourse(course);
    }

    @Override
    public void deleteCourse(int COURSE_ID) {
        courseMapper.deleteCourse(COURSE_ID);
    }
}
