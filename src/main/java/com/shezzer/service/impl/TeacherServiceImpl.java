package com.shezzer.service.impl;

import com.shezzer.mapper.TeacherMapper;
import com.shezzer.pojo.Teacher;
import com.shezzer.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public boolean deleteTeacher(int TEACHER_ID) {
        teacherMapper.deleteTeacher(TEACHER_ID);
        return true;
    }

    @Override
    public void updateTeacherByDepartment(Teacher teacher) {
        teacherMapper.updateTeacherByDepartment(teacher);
    }

    @Override
    public Teacher findTeacherById(int TEACHER_ID) {
        return teacherMapper.findTeacherById(TEACHER_ID);
    }

    @Override
    public List<Teacher> findTeacherByDepartment(int Department_id) {
        return teacherMapper.findTeacherByDepartment(Department_id);
    }
}
