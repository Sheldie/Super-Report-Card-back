package com.shezzer.controller;

import com.shezzer.pojo.Department;
import com.shezzer.pojo.School;
import com.shezzer.pojo.Teacher;
import com.shezzer.pojo.base.Result;
import com.shezzer.service.DepartmentService;
import com.shezzer.service.SchoolService;
import com.shezzer.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private TeacherService teacherService;

    @PostMapping("/addDepartment")
    public Result addDepartment(String DEPARTMENT_NAME, int SCHOOL_ID){
        try{
            School school = schoolService.findSchoolById(SCHOOL_ID);
            if(school != null){
                Department department = new Department(DEPARTMENT_NAME, SCHOOL_ID);
                departmentService.addDepartment(department);
                return Result.success("Success");
            }
            else
                return Result.failed(1, "School doesn't exist.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/listDepartment")
    @ApiOperation(value = "部门列表", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Successful."),
            @ApiResponse(code = 1, message = "No department.")
    })
    public Result listDepartment(){
        try{
            List<Department> dpms = departmentService.findAllByDepartment();
            if(dpms != null)
                return Result.success(dpms);
            else
                return Result.success(1,"No department.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/findDepartmentBySchool")
    public Result findDepartmentBySchool(int SCHOOL_ID){
        try {
            School school = schoolService.findSchoolById(SCHOOL_ID);
            if(school != null){
                return Result.success(departmentService.findDepartmentBySchool(SCHOOL_ID));
            }
            else
                return Result.failed(1, "School dosen't exist.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/deleteDepartment")
    public Result deleteDepartment(int DEPARTMENT_ID){
        try {
            Department department = departmentService.findDepartmentById(DEPARTMENT_ID);
            if(department != null){
                List<Teacher> list = teacherService.findTeacherByDepartment(DEPARTMENT_ID);
                if(list.size() == 0){
                    departmentService.deleteDepartment(DEPARTMENT_ID);
                    return Result.success("Success");
                }
                else
                    return Result.failed(2, "Department not null.");
            }
            else
                return Result.failed(1, "Department doesn't exist.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }
}
