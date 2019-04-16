package com.shezzer.service;

import com.shezzer.pojo.Department;

import java.util.List;

public interface DepartmentService {
    void addDepartment(Department department);
    void deleteDepartment(int DEPARTMENT_ID);
    Department findDepartmentById(int DEPARTMENT_ID);
    List<Department> findAllByDepartment();
    List<Department> findDepartmentBySchool(int SCHOOL_ID);
}
