package com.shezzer.controller;

import com.shezzer.pojo.Course;
import com.shezzer.pojo.Exam;
import com.shezzer.pojo.Grade;
import com.shezzer.pojo.Student;
import com.shezzer.pojo.base.Result;
import com.shezzer.service.CourseService;
import com.shezzer.service.ExamService;
import com.shezzer.service.GradeService;
import com.shezzer.service.StudentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {
    @Autowired
    private ExamService examService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private StudentService studentService;

    @PostMapping("/addExam")
    @ApiOperation(value = "addExam", notes = "添加一次考试，并为所有该班级的学生添加一条空成绩信息")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Exam added."),
            @ApiResponse(code = 1, message = "Course doesn't exist."),
            @ApiResponse(code = 2, message = "Exam already exists.")
    })
    public Result addExam(String EXAM_NAME, int COURSE_ID, String EXAM_DATE){
        try{
            Course course = courseService.findCourseById(COURSE_ID);
            if(course == null)
                return Result.failed(1,"Course doesn't exist.");
            else{
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(EXAM_DATE);
                java.sql.Date sqldate = new java.sql.Date(date.getTime());
                Exam exam = new Exam(EXAM_NAME, COURSE_ID, sqldate);
                Exam temp = examService.checkExam(exam);
                if(temp != null)
                    return Result.failed(2, "Exam already exists.");
                else{
                    examService.addExam(exam);

                    //Get Exam ID.
                    exam = examService.checkExam(exam);

                    //Get Studentlist by class.
                    List<Student> list = studentService.findStudentByClass(course.getCLASS_ID());

                    //Add null grade information for every student.
                    for(Student stu: list){
                        Grade grade = new Grade(exam.getEXAM_ID(),stu.getUSER_ID());
//                        Grade exist = gradeService.findGradeByStudentAndExam(grade);
//                        if(exist == null)
                        gradeService.addGrade(grade);
                    }
                    return Result.success(0, "Exam added.");
                }
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

}
