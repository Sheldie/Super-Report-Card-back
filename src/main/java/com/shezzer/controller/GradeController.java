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

import java.util.*;

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

//    @PostMapping("/findGradeByStudentAndExam")
//    @ApiOperation(value = "查询某学生某次考试的成绩", notes = "")
//    @ApiResponses({
//            @ApiResponse(code = -1, message = "Error"),
//            @ApiResponse(code = 0, message = "Success"),
//            @ApiResponse(code = 1, message = "Exam doesn't exist."),
//            @ApiResponse(code = 2, message = "User doesn't exist."),
//            @ApiResponse(code = 3, message = "Not a student.")
//    })
//    public Result findGradeByStudentAndExam(int EXAM_ID, int STUDENT_ID){
//        try {
//            Exam exam = examService.findExamById(EXAM_ID);
//            if(exam != null){
//                User user = userService.findUserById(STUDENT_ID);
//                if(user != null){
//                    int AY = userService.checkAuthority(STUDENT_ID);
//                    if(AY == 3){
//                        Grade grade = new Grade(EXAM_ID, STUDENT_ID);
//                        grade = gradeService.findGradeByStudentAndExam(grade);
//                        return Result.success(grade);
//                    }
//                    else
//                        return Result.failed(3, "Not a student.");
//                }
//                else{
//                    return Result.failed(2, "User doesn't exist.");
//                }
//            }
//            else{
//                return Result.failed(1, "Exam doesn't exist.");
//            }
//
//        }
//        catch (Exception e){
//            return Result.error(e.toString());
//        }
//    }

    @PostMapping("/findGradeByExam")
    @ApiOperation(value = "查看成绩时：查询某次考试的学生成绩", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success"),
            @ApiResponse(code = 1, message = "Exam doesn't exist.")
    })
    public Result findGradeByExam(int EXAM_ID){
        try{
            Exam exam = examService.findExamById(EXAM_ID);
            if(exam != null){
                List<Map> list = gradeService.findGradeByExam(EXAM_ID);
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

    @PostMapping("/getGradeByExam")
    @ApiOperation(value = "发布成绩时：获取表格", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success"),
            @ApiResponse(code = 1, message = "Exam doesn't exist.")
    })
    public Result getGradeByExam(int EXAM_ID){
        try{
            Exam exam = examService.findExamById(EXAM_ID);
            if(exam != null){
                List<Map> list= gradeService.getGradeByExam(EXAM_ID);
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



    @PostMapping("/updateGrade")
    @ApiOperation(value = "更新成绩", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Successful update."),
            @ApiResponse(code = 1, message = "Don't match.")
    })
    public Result updateGrade(String GRADE_ID, String GRADE){
        try{
            String[] ID = GRADE_ID.split(",");
            String[] GE = GRADE.split(",");

            if(ID.length != GE.length)
                return Result.failed(1, "Don't match.");

            for(int i = 0; i < ID.length; ++i){
                Grade grade = new Grade();
                grade.setGRADE_ID(Integer.parseInt(ID[i]));
                grade.setGRADE(Integer.parseInt(GE[i]));
                gradeService.updateGrade(grade);
            }

            return Result.success(0, "Successful update.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/updateTarget")
    @ApiOperation(value = "设置目标分", notes = "一次更改一个")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Successful update."),
            @ApiResponse(code = 1, message = "Grade doesn't exist."),
    })
    public Result updateTarget(int GRADE_ID, int TARGET){
        try{
            Grade grade = gradeService.findGradeById(GRADE_ID);
            if(grade != null){
                grade.setTARGET(TARGET);
                gradeService.updateTarget(grade);
                return Result.success(0, "Successful update.");
            }
            else {
                return Result.failed(1, "Grade doesn't exist.");
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/gradeSegmentation")
    @ApiOperation(value = "成绩分段查询", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success."),
            @ApiResponse(code = 1, message = "Grade doesn't exist.")
    })
    public Result gradeSegmentation(int EXAM_ID){
        try{
            Map map = gradeService.gradeSegmentation(EXAM_ID);

            if(map != null){
                Map<String,List> res = new HashMap<>();
                Iterator<String> iter = map.keySet().iterator();
                List<String> key = new ArrayList<>();
                List<String> value = new ArrayList<>();

                while(iter.hasNext()){
                    String temp = iter.next();
                    key.add(temp);
                    value.add(map.get(temp).toString());
                }
                res.put("key", key);
                res.put("value", value);
                return Result.success(res);

            }
            else
                return Result.failed(1, "Grade doesn't exist.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/gradeRate")
    @ApiOperation(value = "优良率查询", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success."),
            @ApiResponse(code = 1, message = "Grade doesn't exist.")
    })
    public Result gradeRate(int EXAM_ID){
        try{
            List<Map> list = gradeService.gradeRate(EXAM_ID);
            if(list != null)
                return Result.success(list);
            else
                return Result.failed(1, "Grade doesn't exist.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/getRankByExamAndStudent")
    @ApiOperation(value = "获取某学生某次考试的名次", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success."),
            @ApiResponse(code = 1, message = "User doesn't exist."),
            @ApiResponse(code = 1, message = "Not a student.")
    })
    public Result getRankByExamAndStudent(int EXAM_ID, int STUDENT_ID){
        try {
            User user = userService.findUserById(STUDENT_ID);
            if(user != null){
                int AY = user.getUSER_AUTHORITY();
                if(AY == 3){
                    Exam exam = examService.findExamById(EXAM_ID);
                    if(exam != null){
                        Map map = gradeService.getRankByExamAndStudent(STUDENT_ID,EXAM_ID);
                        if(map != null){
                            return Result.success(map);
                        }
                        else {
                            return Result.failed(4, "The student did not take the test.");
                        }
                    }
                    else {
                        return Result.failed(3, "Exam doesn't exist.");
                    }
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

    @PostMapping("/studentComment")
    @ApiOperation(value = "学生的成绩评价", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success."),
            @ApiResponse(code = 1, message = "Grade doesn't exist."),
    })
    public Result studentComment(int GRADE_ID, int S_SCORE, String S_COMMENT){
        try{
            Grade grade = gradeService.findGradeById(GRADE_ID);
            if(grade != null){
                grade.setS_SCORE(S_SCORE);
                grade.setS_COMMENT(S_COMMENT);
                gradeService.studentComment(grade);
                return Result.success(0, "Successful update.");
            }
            else {
                return Result.failed(1, "Grade doesn't exist.");
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/teacherComment")
    @ApiOperation(value = "教师的成绩评价", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success."),
            @ApiResponse(code = 1, message = "Grade doesn't exist."),
    })
    public Result teacherComment(int GRADE_ID, int T_SCORE, String T_COMMENT){
        try{
            Grade grade = gradeService.findGradeById(GRADE_ID);
            if(grade != null){
                grade.setT_SCORE(T_SCORE);
                grade.setT_COMMENT(T_COMMENT);
                gradeService.teacherComment(grade);
                return Result.success(0, "Successful update.");
            }
            else {
                return Result.failed(1, "Grade doesn't exist.");
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/findGradeById")
    @ApiOperation(value = "根据ID查询成绩", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success.")
    })
    public Result findGradeById(int GRADE_ID){
        try{
            Grade grade = gradeService.findGradeById(GRADE_ID);
            return Result.success(grade);
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/findGradeByStudentAndCourse")
    @ApiOperation(value = "根据学生和课程查成绩", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success.")
    })
    public Result findGradeByStudentAndCourse(int USER_ID, int COURSE_ID){
        try {
            User user = userService.findUserById(USER_ID);
            if(user != null){
                int AY = user.getUSER_AUTHORITY();
                if(AY == 3){
                    return Result.success(gradeService.findGradeByStudentAndCourse(COURSE_ID, USER_ID));
                }
                else{
                    return Result.failed(2, "Not a student.");
                }
            }
            else{
                return Result.failed(1, "User doesn't exist.");
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/getSeatByExam")
    public Result getSeatByExam(int EXAM_ID){
        try{
            Exam exam = examService.findExamById(EXAM_ID);
            if(exam == null)
                return Result.failed(1, "Exam doesn't exist.");
            return Result.success(gradeService.getSeatByExam(EXAM_ID));
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

}
