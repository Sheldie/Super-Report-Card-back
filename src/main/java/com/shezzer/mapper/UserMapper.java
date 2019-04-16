package com.shezzer.mapper;

import com.shezzer.pojo.User;

import java.util.List;

public interface UserMapper {
    void addUser(User user);
    void deleteUser(int USER_ID);
    User findUserByName(String username);
    User findUserById(int id);
    void setAuthority(User user);
    void changePassword(User user);
    List<User> listUser();
}
