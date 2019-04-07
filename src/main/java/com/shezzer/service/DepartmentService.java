package com.shezzer.service;

import com.shezzer.pojo.Department;

import java.util.List;

public interface DepartmentService {
    Department findDepartmentById(int DEPARTMENT_ID);
    List<Department> findAllByDepartment();
}
