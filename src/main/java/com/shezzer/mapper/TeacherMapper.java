package com.shezzer.mapper;

import com.shezzer.pojo.Teacher;

public interface TeacherMapper {
    void addTeacher(Teacher teacher);
    Teacher findTeacherById(int TEACHER_ID);
}
