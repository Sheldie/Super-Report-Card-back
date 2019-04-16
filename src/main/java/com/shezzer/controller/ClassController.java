package com.shezzer.controller;

import com.shezzer.pojo.*;
import com.shezzer.pojo.Class;
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

@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassService classService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/listClass")
    @ApiOperation(value = "班级列表", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Successful."),
            @ApiResponse(code = 1, message = "No class.")
    })
    public Result listClass(){
        try{
            List<Class> classes = classService.findAllByClass();
            if(classes != null)
                return Result.success(classes);
            else
                return Result.success(1,"No class.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/findClassById")
    @ApiOperation(value = "根据ID查看班级信息", notes = "")
    public Result findClassById(int CLASS_ID){
        try{
            Class cs = classService.findClassById(CLASS_ID);
            if(cs != null){
                return Result.success(cs);
            }
            else
                return Result.failed(1, "Class doesn't exist.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/findClassByTeacher")
    @ApiOperation(value = "老师查看自己授课的班级", notes = "")
    public Result findClassByTeacher(int USER_ID){
        try{
            User user = userService.findUserById(USER_ID);
            if(user != null){
                int AY = user.getUSER_AUTHORITY();
                if(AY == 2){
                    return Result.success(courseService.findClassByTeacher(USER_ID));
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

    @PostMapping("/findClassBySchool")
    @ApiOperation(value = "根据学校查找班级", notes = "")
    public Result findClassBySchool(int SCHOOL_ID){
        try {
            School school = schoolService.findSchoolById(SCHOOL_ID);
            if(school != null){
                return Result.success(classService.findClassBySchool(SCHOOL_ID));
            }
            else
                return Result.failed(1, "School doesn't exist.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/getClassBySchool")
//    @ApiOperation(value = "根据学校查找班级", notes = "")
    public Result getClassBySchool(int SCHOOL_ID){
        try {
            School school = schoolService.findSchoolById(SCHOOL_ID);
            if(school != null){
                return Result.success(classService.getClassBySchool(SCHOOL_ID));
            }
            else
                return Result.failed(1, "School doesn't exist.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/addClass")
    public Result addClass(String CLASS_NAME, int SCHOOL_ID, int TEACHER_ID){
        try {
            School school = schoolService.findSchoolById(SCHOOL_ID);
            if(school != null){
                Teacher teacher = teacherService.findTeacherById(TEACHER_ID);
                if(teacher == null)
                    return Result.failed(2, "Teacher doesn't exist.");
                Department department = departmentService.findDepartmentById(teacher.getDEPARTMENT_ID());
                if(department.getSCHOOL_ID() == SCHOOL_ID){
                    Class cs = new Class();
                    cs.setCLASS_NAME(CLASS_NAME);
                    cs.setSCHOOL_ID(SCHOOL_ID);
                    cs.setTEACHER_ID(TEACHER_ID);
                    classService.addClass(cs);
                    return Result.success("Success");
                }
                else
                    return Result.failed(3, "Not the teacher of this school.");
            }
            else
                return Result.failed(1, "School doesn't exist.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/deleteClass")
    public Result deleteClass(int CLASS_ID){
        try {
            Class cs = classService.findClassById(CLASS_ID);
            if(cs != null){
                List<Student> list = studentService.findStudentByClass(CLASS_ID);
                if(list.size() == 0){
                    classService.deleteClass(CLASS_ID);
                    return Result.success("Success.");
                }
                else
                    return Result.failed(2, "Class not null.");
            }
            else
                return Result.failed(1, "Class doesn't exist.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/updateClassByTeacher")
    public Result updateClassByTeacher(int CLASS_ID, int TEACHER_ID){
        try {
            Class cs = classService.findClassById(CLASS_ID);
            if(cs != null){
                Teacher teacher = teacherService.findTeacherById(TEACHER_ID);
                Department department = departmentService.findDepartmentById(teacher.getDEPARTMENT_ID());
                if(department.getSCHOOL_ID() != cs.getSCHOOL_ID())
                    return Result.failed(3, "Not the teacher of this school.");
                if(teacher != null){
                    cs.setTEACHER_ID(TEACHER_ID);
                    classService.updateClassByTeacher(cs);
                    return Result.success("Success");
                }
                else
                    return Result.failed(2, "Teacher doesn't exist.");
            }
            else
                return Result.failed(1, "Class doesn't exist.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }
}
