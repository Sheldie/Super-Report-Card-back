package com.shezzer.controller;

import com.shezzer.pojo.Class;
import com.shezzer.pojo.Course;
import com.shezzer.pojo.Teacher;
import com.shezzer.pojo.base.Result;
import com.shezzer.service.ClassService;
import com.shezzer.service.CourseService;
import com.shezzer.service.TeacherService;
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

    @PostMapping("/addCourse")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success"),
            @ApiResponse(code = 1, message = "Teacher doesn't exist."),
            @ApiResponse(code = 2, message = "Class doesn't exist."),
            @ApiResponse(code = 3, message = "Course already exists.")
    })
    public Result addCourse(String COURSE_NAME, int TEACHER_ID, int CLASS_ID, String SEMESTER){
        try{
            Teacher teacher = teacherService.findTeacherById(TEACHER_ID);
            if(teacher != null){
                Class cs = classService.findClassById(CLASS_ID);
                if(cs != null){
                    Course course = new Course();
                    course.setCOURSE_NAME(COURSE_NAME);
                    course.setTEACHER_ID(TEACHER_ID);
                    course.setCLASS_ID(CLASS_ID);
                    course.setSEMESTER(SEMESTER);

                    Course temp = courseService.checkCourse(course);
                    if(temp == null){
                        courseService.addCourse(course);
                        return Result.success(0, "Course added.");
                    }
                    else {
                        return Result.failed(3, "Course already exists.");
                    }
                }
                else
                    return Result.failed(2,"Class doesn't exist.");
            }
            else {
                return Result.failed(1, "Teacher doesn't exist.");
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

}
