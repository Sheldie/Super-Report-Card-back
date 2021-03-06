package com.shezzer.controller;

import com.shezzer.pojo.Class;
import com.shezzer.pojo.Department;
import com.shezzer.pojo.Teacher;
import com.shezzer.pojo.User;
import com.shezzer.pojo.base.Result;
import com.shezzer.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ClassService classService;

    @PostMapping("/addTeacher")
    @ApiOperation(value = "addTeacher", notes = "用户注册成功后，选择注册教师信息")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Teacher added."),
            @ApiResponse(code = 1, message = "User already has identity."),
            @ApiResponse(code = 2, message = "Department doesn't exist.")
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

    @PostMapping("/findTeacherByDepartment")
    @ApiOperation(value = "根据部门列出教师名单", notes = "用户注册成功后，选择注册教师信息")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success"),
            @ApiResponse(code = 1, message = "Department doesn't exist."),
    })
    public Result findTeacherByDepartment(int DEPARTMENT_ID){
        try{
            Department department = departmentService.findDepartmentById(DEPARTMENT_ID);
            if(department != null){
                List<Teacher> list = teacherService.findTeacherByDepartment(DEPARTMENT_ID);
                return Result.success(list);
            }
            else{
                return Result.failed(1,"Department doesn't exist.");
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/deleteTeacher")
    public Result deleteTeacher(int TEACHER_ID){
        try {
            User user = userService.findUserById(TEACHER_ID);
            if(user != null){
                int AY = user.getUSER_AUTHORITY();
                if(AY == 2){
                    List<Map> list = courseService.findCourseByTeacher(TEACHER_ID);
                    List<Class> cs = classService.findClassByHead(TEACHER_ID);
                    if(list.size() != 0)
                        return Result.failed(3, "Teacher has courses.");
                    if(cs.size() != 0)
                        return Result.failed(4, "Teacher is a head teacher.");
                    teacherService.deleteTeacher(TEACHER_ID);
                    userService.deleteUser(TEACHER_ID);
                    return Result.success("Success.");
                }
                else
                    return Result.failed(2, "Not a teacher.");
            }
            else
                return Result.failed(1, "User doesn't exist.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/updateTeacherByDepartment")
    public Result updateTeacherByDepartment(int TEACHER_ID, int DEPARTMENT_ID){
        try {
            Teacher teacher = teacherService.findTeacherById(TEACHER_ID);
            if(teacher != null){
                Department department = departmentService.findDepartmentById(DEPARTMENT_ID);
                if(department != null){
                    teacher.setDEPARTMENT_ID(DEPARTMENT_ID);
                    teacherService.updateTeacherByDepartment(teacher);
                    return Result.success("Success");
                }
                return Result.failed(2, "Department doesn't exist.");
            }
            else
                return Result.failed(1, "Teacher doesn't exist.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

}
