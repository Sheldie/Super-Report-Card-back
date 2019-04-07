package com.shezzer.service.impl;

import com.shezzer.mapper.UserMapper;
import com.shezzer.pojo.User;
import com.shezzer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User register(User user) {
        User temp = userMapper.findUserByName(user.getUSER_NAME());
        if (temp != null) {
            return null;
        }
        userMapper.addUser(user);
        return userMapper.findUserByName(user.getUSER_NAME());
    }

    @Override
    public boolean checkUsername(String username) {
        User temp = userMapper.findUserByName(username);
        if(temp == null)
            return false;
        else
            return true;
    }

    @Override
    public User checkLogin(String username, String password) {
        User user = userMapper.findUserByName(username);
        if (user != null && user.getUSER_PASSWORD().equals(password)) {
            return user;
        }
        return null;
    }

    @Override
    public int checkAuthority(int id) {
        User user = userMapper.findUserById(id);
        if(user != null){
            return user.getUSER_AUTHORITY();
        }
        else
            return -2;
    }

    @Override
    public boolean setAuthority(User user) {
        User temp = userMapper.findUserById(user.getUSER_ID());
        if(temp != null){
            userMapper.setAuthority(user);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean changePassword(User user) {
        userMapper.changePassword(user);
        return true;
    }


    @Override
    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }

    @Override
    public List<User> listUser() {
        return userMapper.listUser();
    }


}
