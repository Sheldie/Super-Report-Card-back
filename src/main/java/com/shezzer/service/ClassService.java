package com.shezzer.service;

import com.shezzer.pojo.Class;

import java.util.List;

public interface ClassService {
    Class findClassById(int CLASS_ID);
    List<Class> findAllByClass();
}
