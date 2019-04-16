package com.shezzer.service.impl;

import com.shezzer.mapper.StudentMapper;
import com.shezzer.pojo.Student;
import com.shezzer.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public boolean addStudent(Student student) {
        studentMapper.addStudent(student);
        return true;
    }

    @Override
    public void deleteStudent(int STUDENT_ID) {
        studentMapper.deleteStudent(STUDENT_ID);
    }

    @Override
    public Student checkStudent(Student student) {
        return studentMapper.checkStudent(student);
    }

    @Override
    public List<Student> findStudentByClass(int CLASS_ID) {
        return studentMapper.findStudentByClass(CLASS_ID);
    }
}
