package com.shezzer.service.impl;

import com.shezzer.mapper.DepartmentMapper;
import com.shezzer.pojo.Department;
import com.shezzer.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public void addDepartment(Department department) {
        departmentMapper.addDepartment(department);
    }

    @Override
    public void deleteDepartment(int DEPARTMENT_ID) {
        departmentMapper.deleteDepartment(DEPARTMENT_ID);
    }

    @Override
    public Department findDepartmentById(int DEPARTMENT_ID) {
        return departmentMapper.findDepartmentById(DEPARTMENT_ID);
    }

    @Override
    public List<Department> findAllByDepartment() {
        return departmentMapper.findAllDepartment();
    }

    @Override
    public List<Department> findDepartmentBySchool(int SCHOOL_ID) {
        return departmentMapper.findDepartmentBySchool(SCHOOL_ID);
    }
}
