package com.shezzer.service;

import com.shezzer.pojo.Teacher;

import java.util.List;

public interface TeacherService {
    boolean teacherRegister(Teacher teacher);
    Teacher findTeacherById(int TEACHER_ID);
    List<Teacher> findTeacherByDepartment(int Department_id);
}
