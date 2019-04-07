package com.shezzer.mapper;

import com.shezzer.pojo.Department;

import java.util.List;

public interface DepartmentMapper {
    Department findDepartmentById(int DEPARTMENT_ID);
    List<Department> findAllDepartment();
}
