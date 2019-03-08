package com.shezzer.controller;

import com.shezzer.pojo.Subject;
import com.shezzer.pojo.base.Result;
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
