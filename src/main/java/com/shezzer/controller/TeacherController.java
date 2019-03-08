package com.shezzer.controller;

import com.shezzer.pojo.Department;
import com.shezzer.pojo.Teacher;
import com.shezzer.pojo.User;
import com.shezzer.pojo.base.Result;
import com.shezzer.service.DepartmentService;
import com.shezzer.service.TeacherService;
import com.shezzer.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/addTeacher")
    @ApiOperation(value = "addTeacher", notes = "用户注册成功后，选择注册教师信息")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Teacher added."),
            @ApiResponse(code = 1, message = "User already has identity."),
            @ApiResponse(code = 2, message = "Department doesn't exist."),
//            @ApiResponse(code = 3, message = "Student already exists.")
    })
    public Result addTeacher(int USER_ID, int TEACHER_NUM, String TEACHER_NAME, int DEPARTMENT_ID){
        try{
            User user = userService.findUserById(USER_ID);
            int AY = user.getUSER_AUTHORITY();
            if(AY == -1){
                Department department = departmentService.findDepartmentById(DEPARTMENT_ID);
                if(department != null){
                    Teacher teacher = new Teacher();
                    teacher.setUSER_ID(USER_ID);
                    teacher.setTEACHER_NUM(TEACHER_NUM);
                    teacher.setTEACHER_NAME(TEACHER_NAME);
                    teacher.setDEPARTMENT_ID(DEPARTMENT_ID);
                    teacherService.teacherRegister(teacher);
                    user.setUSER_AUTHORITY(2);
                    userService.setAuthority(user);
                    return Result.success("Teacher added.");
                }
                else{
                    return Result.failed(2,"Department doesn't exist.");
                }
            }
            else
                return Result.failed(1,"User already has identity.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }




}
