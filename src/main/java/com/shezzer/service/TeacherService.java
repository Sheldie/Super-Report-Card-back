package com.shezzer.service;

import com.shezzer.pojo.Teacher;

public interface TeacherService {
    boolean teacherRegister(Teacher teacher);
    Teacher findTeacherById(int TEACHER_ID);
}
