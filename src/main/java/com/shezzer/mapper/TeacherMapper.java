package com.shezzer.mapper;

import com.shezzer.pojo.Teacher;

import java.util.List;

public interface TeacherMapper {
    void addTeacher(Teacher teacher);
    Teacher findTeacherById(int TEACHER_ID);
    List<Teacher> findTeacherByDepartment(int DEPARTMENT_ID);
}
