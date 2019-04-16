package com.shezzer.mapper;

import com.shezzer.pojo.Student;

import java.util.List;

public interface StudentMapper {
    void addStudent(Student student);
    void deleteStudent(int STUDENT_ID);
    Student checkStudent(Student student);
    List<Student> findStudentByClass(int CLASS_ID);
}
