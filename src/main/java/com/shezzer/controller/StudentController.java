package com.shezzer.controller;


import com.shezzer.pojo.Class;
import com.shezzer.pojo.Course;
import com.shezzer.pojo.Student;
import com.shezzer.pojo.User;
import com.shezzer.pojo.base.Result;
import com.shezzer.service.ClassService;
import com.shezzer.service.CourseService;
import com.shezzer.service.StudentService;
import com.shezzer.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassService classService;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    @PostMapping("/addStudent")
    @ApiOperation(value = "学生注册", notes = "用户注册成功后，选择注册学生信息")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Student added."),
            @ApiResponse(code = 1, message = "User doesn't exist."),
            @ApiResponse(code = 2, message = "User already has identity."),
            @ApiResponse(code = 3, message = "Class doesn't exist."),
            @ApiResponse(code = 4, message = "Student already exists.")
    })
    public Result addStudent(int USER_ID, String STUDENT_NAME, String STUDENT_NUM, int CLASS_ID){
        try{
            User user = userService.findUserById(USER_ID);
            if(user == null)
                return Result.failed(1, "User doesn't exist.");
            else {
                int AY = user.getUSER_AUTHORITY();
                if(AY != -1)
                    return Result.failed(2, "User already has identity.");
                else{
                    Class cs = classService.findClassById(CLASS_ID);
                    if(cs == null)
                        return Result.failed(3, "Class doesn't exist.");
                    else {
                        Student student = new Student();
                        student.setSTUDENT_NAME(STUDENT_NAME);
                        student.setSTUDENT_NUM(STUDENT_NUM);
                        student.setCLASS_ID(CLASS_ID);

                        Student temp = studentService.checkStudent(student);
                        if(temp != null)
                            return Result.failed(4, "Student already exists.");
                        else {
                            student.setUSER_ID(USER_ID);
                            studentService.addStudent(student);
                            user.setUSER_AUTHORITY(3);
                            userService.setAuthority(user);
                            return Result.success("Student added.");
                        }
                    }
                }
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/findStudentByClass")
    @ApiOperation(value = "根据班级列出学生名单", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success."),
            @ApiResponse(code = 1, message = "Class doesn't exist."),
    })
    public Result findStudentByClass(int CLASS_ID){
        try{
            Class cs = classService.findClassById(CLASS_ID);
            if(cs != null) {
                List<Student> list = studentService.findStudentByClass(CLASS_ID);
                return Result.success(list);
            }
            else {
                return Result.failed(1, "Class doesn't exist.");
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/findStudentByCourse")
    @ApiOperation(value = "根据课程列出学生名单", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success."),
            @ApiResponse(code = 1, message = "Class doesn't exist."),
    })
    public Result findStudentByCourse(int COURSE_ID){
        try {
            Course course = courseService.findCourseById(COURSE_ID);
            if(course != null){
                return Result.success(studentService.findStudentByClass(course.getCLASS_ID()));
            }
            else{
                return Result.failed(1, "Course doesn't exist.");
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/deleteStudent")
    public Result deleteStudent(int STUDENT_ID){
        try {
            User user = userService.findUserById(STUDENT_ID);
            if(user != null){
                int AY = user.getUSER_AUTHORITY();
                if(AY == 3){
                    studentService.deleteStudent(STUDENT_ID);
                    userService.deleteUser(STUDENT_ID);
                    return Result.success("Success");
                }
                else
                    return Result.failed(2, "Not a student.");
            }
            else
                return Result.failed(1, "User doesn't exist.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

}
