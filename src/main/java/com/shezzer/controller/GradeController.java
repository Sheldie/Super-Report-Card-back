package com.shezzer.controller;


import com.shezzer.pojo.Exam;
import com.shezzer.pojo.Grade;
import com.shezzer.pojo.User;
import com.shezzer.pojo.base.Result;
import com.shezzer.service.ExamService;
import com.shezzer.service.GradeService;
import com.shezzer.service.UserService;
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
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;
    @Autowired
    private UserService userService;
    @Autowired
    private ExamService examService;

    @PostMapping("/findGradeByStudentAndSemester")
    @ApiOperation(value = "根据学生ID和学期，查询该学生该学期的成绩", notes = "学期的格式：如 20182019A 或 20182019S")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success"),
            @ApiResponse(code = 1, message = "User not found."),
            @ApiResponse(code = 2, message = "Not a student.")
    })
    public Result findGradeByStudentAndSemester(int STUDENT_ID, String SEMESTER){
        try{
            User user = userService.findUserById(STUDENT_ID);
            if(user != null){
                int AY = userService.checkAuthority(STUDENT_ID);
                if(AY == 3){
                    List<Map> list = gradeService.findGradeByStudentAndSemester(STUDENT_ID, SEMESTER);
                    return Result.success(list);
                }
                else
                    return Result.failed(2, "Not a student.");
            }
            else{
                return Result.failed(1, "User not found.");
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/findGradeByStudentAndExam")
    @ApiOperation(value = "查询某学生某次考试的成绩", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success"),
            @ApiResponse(code = 1, message = "Exam doesn't exist."),
            @ApiResponse(code = 2, message = "User doesn't exist."),
            @ApiResponse(code = 3, message = "Not a student.")
    })
    public Result findGradeByStudentAndExam(int EXAM_ID, int STUDENT_ID){
        try {
            Exam exam = examService.findExamById(EXAM_ID);
            if(exam != null){
                User user = userService.findUserById(STUDENT_ID);
                if(user != null){
                    int AY = userService.checkAuthority(STUDENT_ID);
                    if(AY == 3){
                        Grade grade = new Grade(EXAM_ID, STUDENT_ID);
                        grade = gradeService.findGradeByStudentAndExam(grade);
                        return Result.success(grade);
                    }
                    else
                        return Result.failed(3, "Not a student.");
                }
                else{
                    return Result.failed(2, "User doesn't exist.");
                }
            }
            else{
                return Result.failed(1, "Exam doesn't exist.");
            }

        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/findGradeByExam")
    @ApiOperation(value = "查询某次考试的学生成绩", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success"),
            @ApiResponse(code = 1, message = "Exam doesn't exist.")
    })
    public Result findGradeByExam(int EXAM_ID){
        try{
            Exam exam = examService.findExamById(EXAM_ID);
            if(exam != null){
                List<Grade> list = gradeService.findGradeByExam(EXAM_ID);
                return Result.success(list);
            }
            else {
                return Result.failed(1, "Exam doesn't exist.");
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/gradeData")
    @ApiOperation(value = "某次考试的成绩分析", notes = "目前只有平均分、最高分、最低分")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success"),
            @ApiResponse(code = 1, message = "Exam doesn't exist.")
    })
    public Result gradeData(int EXAM_ID){
        try{
            Exam exam = examService.findExamById(EXAM_ID);
            if(exam != null){
                Map<String, String> map = gradeService.gradeData(EXAM_ID);
                return Result.success(map);
            }
            else {
                return Result.failed(1, "Exam doesn't exist.");
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }



}