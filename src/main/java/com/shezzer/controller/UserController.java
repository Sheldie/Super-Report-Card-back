package com.shezzer.controller;


import com.shezzer.pojo.User;
import com.shezzer.pojo.base.Result;
import com.shezzer.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    //Check username.
    //0 - Exists.
    //1 - Not exist.
    @GetMapping("/checkUsername")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Exists."),
            @ApiResponse(code = 1, message = "Not exist.")
    })
    public Result usernameCheck(String username){
        try{
            if(userService.checkUsername(username))
                return Result.success(0,"Exists");
            else
                return Result.success(1,"Not exist");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    //Login.
    @PostMapping("/login")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "返回用户ID"),
            @ApiResponse(code = 1, message = "Incorrect password."),
            @ApiResponse(code = 2, message = "Username doesn't exist.")
    })
    public Result userLogin(String username, String password){
        try{
            if(userService.checkUsername(username)) {
                User user = userService.checkLogin(username,password);
                if(user == null)
                    return Result.failed(1,"Incorrect password.");
                else
                    return Result.success(0,String.valueOf(user.getUSER_ID()));
            }
            else{
                return Result.failed(2,"Username doesn't exist.");
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    //Register.
    @PostMapping("/register")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Successful registration."),
            @ApiResponse(code = 1, message = "Username already exists.")
    })
    public Result userRegister(String username, String password){
        try {
            if(!userService.checkUsername(username)){
                User user = new User();
                user.setUSER_NAME(username);
                user.setUSER_PASSWORD(password);
                user = userService.register(user);
                return Result.success(user.getUSER_ID());
            }
            else
                return Result.failed(1,"Username already exists.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }



    //0 - SUPERADMIN
    //1 - ADMIN
    //2 - TEACHER
    //3 - STUDENT
    //-1 - UNREGISTERED
    @GetMapping("/authority")
    @ApiOperation(value = "checkAuthority", notes = " Data : \n 0 - SUPERADMIN\n" +
            "    1 - ADMIN\n" +
            "    2 - TEACHER\n" +
            "    3 - STUDENT\n" +
            "    -1 - UNREGISTERED\n\n"
//    + "Code: \n-1 - Error \n  0 - Success \n 1 - User not found."
    )
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "返回权限值"),
            @ApiResponse(code = 1, message = "User not found.")
    })
    public Result checkAuthority(int USER_ID){
        try{
            int ans = userService.checkAuthority(USER_ID);
            if(ans == -2)
                return Result.failed(1,"User not found.");
            else
                return Result.success(0, String.valueOf(ans));
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/changePwd")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "Password has changed."),
            @ApiResponse(code = 1, message = "Incorrect old password.")
    })
    public Result changePassword(String username, String oldPwd, String newPwd){
        try{
            User user = userService.checkLogin(username, oldPwd);
            if(user == null)
                return Result.failed(1,"Incorrect password.");
            else{
                user.setUSER_PASSWORD(newPwd);
                userService.changePassword(user);
                return Result.success(0,"Password has changed.");
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }

    @PostMapping("/infoUser")
    public Result<User> infoUser(int USER_ID){
        try{
            User user = userService.findUserById(USER_ID);
            if(user == null)
                return Result.failed(1,"User not found.");
            else{
                return Result.success(user);
            }
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }



    @PostMapping("/listUser")
    @ApiResponses({
            @ApiResponse(code = -1, message = "Error"),
            @ApiResponse(code = 0, message = "返回全部用户信息"),
            @ApiResponse(code = 1, message = "No user.")
    })
    public Result<List<User>> listUser(){
        try{
            List<User> users = userService.listUser();
            if(users != null)
                return Result.success(users);
            else
                return Result.success(1,"No user.");
        }
        catch (Exception e){
            return Result.error(e.toString());
        }
    }


}
