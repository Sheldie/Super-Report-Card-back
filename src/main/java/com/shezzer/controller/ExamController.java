package com.shezzer.controller;

import com.shezzer.pojo.*;
import com.shezzer.pojo.base.Result;
import com.shezzer.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private UserService userService;

    @PostMapping("/addExam")
    @ApiOperation(value = "添加一次考试，并为所有该班级的学生添加一条空成绩信息", notes = "日期格式：YYYY-MM-DD")
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
                    int seat = 1;
                    for(Student stu: list){
                        Grade grade = new Grade(exam.getEXAM_ID(),stu.getUSER_ID(),seat);
//                        Grade exist = gradeService.findGradeByStudentAndExam(grade);
//                        if(exist == null)
                        gradeService.addGrade(grade);
                        ++seat;
                    }
                    return Result.success(0, "Exam added.");
                }
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/infoExam")
    @ApiOperation(value = "根据考试ID，查询考试信息", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success"),
            @ApiResponse(code = 1, message = "Exam doesn't exist.")
    })
    public Result infoExam(int EXAM_ID){
        try {
            Exam exam = examService.findExamById(EXAM_ID);
            if(exam != null){
                return Result.success(exam);
            }
            else {
                return Result.failed(1 , "Exam doesn't exist.");
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/courseExam")
    @ApiOperation(value = "根据课程查询考试信息", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success"),
            @ApiResponse(code = 1, message = "Course doesn't exist.")
    })
    public Result courseExam(int COURSE_ID){
        try{
            Course course = courseService.findCourseById(COURSE_ID);
            if(course != null){
                List<Map> list = examService.findExamByCourse(COURSE_ID);
                return Result.success(list);
            }
            else
                return Result.failed(1, "Course doesn't exist.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/findExamByTeacher")
    @ApiOperation(value = "根据老师查询考试信息", notes = "GRADE和TARGET此处不需要，用于学生查询")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success"),
            @ApiResponse(code = 1, message = "User doesn't exist."),
            @ApiResponse(code = 2, message = "Not a teacher.")
    })
    public Result findExamByTeacher(int TEACHER_ID){
        try{
            User user = userService.findUserById(TEACHER_ID);
            if(user != null){
                int AY = user.getUSER_AUTHORITY();
                if(AY == 2){
                    List<Map> list = examService.findExamByTeacher(TEACHER_ID);
                    return Result.success(list);
                }
                else {
                    return Result.failed(2, "Not a teacher.");
                }
            }
            else {
                return Result.failed(1, "User doesn't exist.");
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/findExamByStudent")
    @ApiOperation(value = "根据学生查询考试信息", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success"),
            @ApiResponse(code = 1, message = "User doesn't exist."),
            @ApiResponse(code = 2, message = "Not a student.")
    })
    public Result findExamByStudent(int STUDENT_ID){
        try{
            User user = userService.findUserById(STUDENT_ID);
            if(user != null){
                int AY = user.getUSER_AUTHORITY();
                if(AY == 3){
                    List<Map> list = examService.findExamByStudent(STUDENT_ID);
                    return Result.success(list);
                }
                else {
                    return Result.failed(2, "Not a student.");
                }
            }
            else {
                return Result.failed(1, "User doesn't exist.");
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/updateExam")
    @ApiOperation(value = "更新考试信息", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Successful update."),
            @ApiResponse(code = 1, message = "Exam doesn't exist.")
    })
    public Result updateExam(int EXAM_ID, String EXAM_NAME, String EXAM_DATE){
        try{
            Exam exam = examService.findExamById(EXAM_ID);
            if(exam != null){
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(EXAM_DATE);
                java.sql.Date sqldate = new java.sql.Date(date.getTime());
                exam.setEXAM_NAME(EXAM_NAME);
                exam.setEXAM_DATE(sqldate);
                examService.updateExam(exam);
                return Result.success(0, "Successful update.");
            }
            else {
                return Result.failed(1, "Exam doesn't exist.");
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/checkCount")
    @ApiOperation(value = "校准考试人数", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Successful update."),
    })
    public Result checkCount(int EXAM_ID){
        try{
            Exam exam = examService.findExamById(EXAM_ID);
            Course course = courseService.findCourseById(exam.getCOURSE_ID());
            List<Student> list = studentService.findStudentByClass(course.getCLASS_ID());

            int seat = 1;
            for(Student stu: list){
                Grade grade = new Grade(exam.getEXAM_ID(),stu.getUSER_ID(),seat);
                Grade exist = gradeService.findGradeByStudentAndExam(grade);
                if(exist == null)
                    gradeService.addGrade(grade);
                else{
                    exist.setSEAT(seat);
                    gradeService.updateSeat(exist);
                }
                ++seat;
            }
            return Result.success("Success");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/deleteExam")
    @ApiOperation(value = "删除考试", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Successful update."),
    })
    public Result deleteExam(int EXAM_ID){
        try{
            Exam exam = examService.findExamById(EXAM_ID);
            if(exam != null){
                List<Map> list = gradeService.findGradeByExam(EXAM_ID);
                for(Map map: list){
                    gradeService.deleteGrade((int) map.get("GRADE_ID"));
                }
                examService.deleteExam(exam);
                return Result.success("Success");
            }
            else{
                return Result.failed(1, "Exam doesn't exist.");
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/seatInOrder")
    public Result seatInOrder(int EXAM_ID){
        try{
            Exam exam = examService.findExamById(EXAM_ID);
            if(exam == null)
                return Result.failed(1, "Exam doesn't exist.");

            List<Grade> list = gradeService.findSeatByExam(EXAM_ID);
            int seat = 1;
            for(Grade grade: list){
                grade.setSEAT(seat);
                gradeService.updateSeat(grade);
                ++seat;
            }
            return Result.success("Success");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/seatInReverseOrder")
    public Result seatInReverseOrder(int EXAM_ID){
        try{
            Exam exam = examService.findExamById(EXAM_ID);
            if(exam == null)
                return Result.failed(1, "Exam doesn't exist.");

            List<Grade> list = gradeService.findSeatByExam(EXAM_ID);
            int seat = list.size();
            for(Grade grade: list){
                grade.setSEAT(seat);
                gradeService.updateSeat(grade);
                --seat;
            }
            return Result.success("Success");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/seatInRandomOrder")
    public Result seatInRandomOrder(int EXAM_ID){
        try{
            Exam exam = examService.findExamById(EXAM_ID);
            if(exam == null)
                return Result.failed(1, "Exam doesn't exist.");

            List<Grade> list = gradeService.findSeatByExam(EXAM_ID);
            List<Integer> num = new ArrayList<>();

            int count = 0;
            for(Grade grade: list){
                num.add(++count);
            }

            for(int i = list.size() - 1; i >= 0; --i){
                int random = (int)(Math.random() * i);
                int swap = num.get(random);
                int temp = num.get(i);
                num.set(i,swap);
                num.set(random,temp);

                list.get(i).setSEAT(num.get(i));
                gradeService.updateSeat(list.get(i));
            }
            return Result.success("Success");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

}
