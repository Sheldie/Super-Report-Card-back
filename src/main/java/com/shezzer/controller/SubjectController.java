package com.shezzer.controller;

import com.shezzer.pojo.Course;
import com.shezzer.pojo.Subject;
import com.shezzer.pojo.base.Result;
import com.shezzer.service.CourseService;
import com.shezzer.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;
    @Autowired
    CourseService courseService;

    @PostMapping("/addSubject")
    public Result addSubject(String SUBJECT_NAME){
        try{
            Subject subject = new Subject(SUBJECT_NAME);
            subjectService.addSubject(subject);
            return Result.success("Success");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/updateSubject")
    public Result updateSubject(int SUBJECT_ID, String SUBJECT_NAME){
        try{
            Subject subject = subjectService.findSubjectById(SUBJECT_ID);
            if(subject == null)
                return Result.failed(1, "Subject doesn't exist.");
            subject.setSUBJECT_NAME(SUBJECT_NAME);
            subjectService.updateSubject(subject);
            return Result.success("Success");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/deleteSubject")
    public Result deleteSubject(int SUBJECT_ID){
        try{
            Subject subject = subjectService.findSubjectById(SUBJECT_ID);
            if(subject == null)
                return Result.failed(1, "Subject doesn't exist.");
            List<Course> list = courseService.findCourseBySubject(SUBJECT_ID);
            if(list.size() != 0)
                return Result.failed(2, "Subject has corresponding courses.");
            subjectService.deleteSubject(SUBJECT_ID);
            return Result.success("Success");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/listSubject")
    public Result listSubject(){
        try{
            List<Subject> list = subjectService.listSubject();
            return Result.success(list);
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }
}
