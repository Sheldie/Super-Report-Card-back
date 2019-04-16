package com.shezzer.mapper;

import com.shezzer.pojo.Teacher;

import java.util.List;

public interface TeacherMapper {
    void addTeacher(Teacher teacher);
    void deleteTeacher(int TEACHER_ID);
    void updateTeacherByDepartment(Teacher teacher);
    Teacher findTeacherById(int TEACHER_ID);
    List<Teacher> findTeacherByDepartment(int DEPARTMENT_ID);
}
