package com.shezzer.service;

import com.shezzer.pojo.User;

import java.util.List;

public interface UserService {
    User register(User user);
    boolean checkUsername(String username);
    User checkLogin(String username, String password);
    int checkAuthority(int id);
    boolean setAuthority(User user);
    boolean changePassword(User user);
    boolean deleteUser(int USER_ID);
    User findUserById(int id);
    List<User> listUser();
}
