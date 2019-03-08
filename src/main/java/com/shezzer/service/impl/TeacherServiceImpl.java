package com.shezzer.service.impl;

import com.shezzer.mapper.TeacherMapper;
import com.shezzer.pojo.Teacher;
import com.shezzer.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public boolean teacherRegister(Teacher teacher) {
        teacherMapper.addTeacher(teacher);
        return true;
    }

    @Override
    public Teacher findTeacherById(int TEACHER_ID) {
        return teacherMapper.findTeacherById(TEACHER_ID);
    }
}