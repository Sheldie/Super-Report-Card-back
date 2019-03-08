package com.shezzer.service.impl;

import com.shezzer.mapper.DepartmentMapper;
import com.shezzer.pojo.Department;
import com.shezzer.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public Department findDepartmentById(int DEPARTMENT_ID) {
        return departmentMapper.findDepartmentById(DEPARTMENT_ID);
    }
}
