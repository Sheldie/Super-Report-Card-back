package com.shezzer.service;

import com.shezzer.pojo.Student;

import java.util.List;

public interface StudentService {
    boolean addStudent(Student student);
    void deleteStudent(int STUDENT_ID);
    Student checkStudent(Student student);
    List<Student> findStudentByClass(int CLASS_ID);
}
