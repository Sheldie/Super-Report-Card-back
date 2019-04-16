package com.shezzer.mapper;

import com.shezzer.pojo.Department;

import java.util.List;

public interface DepartmentMapper {
    Department findDepartmentById(int DEPARTMENT_ID);
    List<Department> findAllDepartment();
    void addDepartment(Department department);
    void deleteDepartment(int DEPARTMENT_ID);
    List<Department> findDepartmentBySchool(int SCHOOL_ID);
}
