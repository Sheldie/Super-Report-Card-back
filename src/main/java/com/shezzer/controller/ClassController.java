package com.shezzer.controller;

import com.shezzer.pojo.Class;
import com.shezzer.pojo.base.Result;
import com.shezzer.service.ClassService;
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
}
