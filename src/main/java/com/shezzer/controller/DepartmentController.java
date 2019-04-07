package com.shezzer.controller;

import com.shezzer.pojo.Department;
import com.shezzer.pojo.base.Result;
import com.shezzer.service.DepartmentService;
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
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/listDepartment")
    @ApiOperation(value = "部门列表", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Successful."),
            @ApiResponse(code = 1, message = "No department.")
    })
    public Result listDepartment(){
        try{
            List<Department> dpms = departmentService.findAllByDepartment();
            if(dpms != null)
                return Result.success(dpms);
            else
                return Result.success(1,"No department.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }
}
