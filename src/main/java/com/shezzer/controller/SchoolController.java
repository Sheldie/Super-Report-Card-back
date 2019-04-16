package com.shezzer.controller;

import com.shezzer.pojo.School;
import com.shezzer.pojo.User;
import com.shezzer.pojo.base.Result;
import com.shezzer.service.SchoolService;
import com.shezzer.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private UserService userService;

    @PostMapping("/addSchool")
    @ApiOperation(value = "addSchool", notes = "用户注册后，选择注册管理员（学校账号），添加学校信息")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "School added."),
            @ApiResponse(code = 1, message = "School already exists."),
            @ApiResponse(code = 2, message = "User already has identity.")
    })
    public Result addSchool(int USER_ID, String schoolname, String country, String province, String city){
        try{
            School school = new School();
            school.setUSER_ID(USER_ID);
            school.setSCHOOL_NAME(schoolname);
            school.setSCHOOL_COUNTRY(country);
            school.setSCHOOL_PROVINCE(province);
            school.setSCHOOL_CITY(city);
            School temp = schoolService.checkSchool(school);

            if(temp == null){
                User user = userService.findUserById(USER_ID);
                int AY = user.getUSER_AUTHORITY();
                if(AY == -1){
                    schoolService.addSchool(school);
                    user.setUSER_AUTHORITY(1);
                    userService.setAuthority(user);
                    return Result.success(0,"School added.");
                }
                else
                    return Result.failed(2,"User already has identity.");
            }
            else
                return Result.failed(1, "School already exists.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/updateSchool")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Successful update."),
            @ApiResponse(code = 1, message = "School doesn't exist.")
    })
    public Result updateSchool(int SCHOOL_ID, String schoolname, String country, String province, String city){
        try{
            School school = schoolService.findSchoolById(SCHOOL_ID);
            if(school != null){
                school.setSCHOOL_NAME(schoolname);
                school.setSCHOOL_COUNTRY(country);
                school.setSCHOOL_PROVINCE(province);
                school.setSCHOOL_CITY(city);
                schoolService.updateSchool(school);
                return Result.success(0,"Successful update.");
            }
            else
                return Result.failed(1,"School doesn't exist.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/infoSchool")
    @ApiOperation(value = "用户查询自己学校的信息", notes = "")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Success"),
            @ApiResponse(code = 1, message = "User doesn't exist."),
            @ApiResponse(code = 2, message = "Permission denied.")
    })
    public Result<School> infoSchool(int USER_ID){
        try {
            User user = userService.findUserById(USER_ID);
            if(user != null) {
                int AY = user.getUSER_AUTHORITY();
                if(AY != -1 && AY != 0){
                    School school = schoolService.findSchoolByUser(user);
                    return Result.success(school);
                }
                else {
                    return Result.failed(2, "Permission denied.");
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

    @PostMapping("/listSchool")
    @ApiOperation(value = "listSchool", notes = "查询全部学校")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "所有学校信息"),
            @ApiResponse(code = 1, message = "No school.")
    })
    public Result<List<School>> listSchool(){
        try{
            List<School> list = schoolService.listSchool();
            if(list != null)
                return Result.success(list);
            else
                return Result.success(1,"No school.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

}
