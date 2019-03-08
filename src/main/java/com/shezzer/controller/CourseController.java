package com.shezzer.controller;

import com.shezzer.pojo.Class;
import com.shezzer.pojo.Course;
import com.shezzer.pojo.Subject;
import com.shezzer.pojo.Teacher;
import com.shezzer.pojo.base.Result;
import com.shezzer.service.ClassService;
import com.shezzer.service.CourseService;
import com.shezzer.service.SubjectService;
import com.shezzer.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ClassService classService;
    @Autowired
    private SubjectService subjectService;

    @PostMapping("/addCourse")
    @ApiOperation(value = "添加课程", notes = "学期的格式：如 20182019A 或 20182019S")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success"),
            @ApiResponse(code = 1, message = "Subject doesn't exist."),
            @ApiResponse(code = 2, message = "Teacher doesn't exist."),
            @ApiResponse(code = 3, message = "Class doesn't exist."),
            @ApiResponse(code = 4, message = "Course already exists.")
    })
    public Result addCourse(int SUBJECT_ID, int TEACHER_ID, int CLASS_ID, String SEMESTER){
        try{
            Subject subject = subjectService.findSubjectById(SUBJECT_ID);
            if(subject != null){
                Teacher teacher = teacherService.findTeacherById(TEACHER_ID);
                if(teacher != null){
                    Class cs = classService.findClassById(CLASS_ID);
                    if(cs != null){
                        Course course = new Course(SUBJECT_ID,TEACHER_ID,CLASS_ID,SEMESTER);
                        Course temp = courseService.checkCourse(course);
                        if(temp == null){
                            courseService.addCourse(course);
                            return Result.success(0, "Course added.");
                        }
                        else {
                            return Result.failed(4, "Course already exists.");
                        }
                    }
                    else
                        return Result.failed(3,"Class doesn't exist.");
                }
                else {
                    return Result.failed(2, "Teacher doesn't exist.");
                }
            }
            else{
                return Result.failed(1, "Subject doesn't exist.");
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

}
