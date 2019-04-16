package com.shezzer.service;

import com.shezzer.pojo.Class;

import java.util.List;
import java.util.Map;

public interface ClassService {
    Class findClassById(int CLASS_ID);
    List<Class> findAllByClass();
    List<Class> findClassBySchool(int SCHOOL_ID);
    List<Map> getClassBySchool(int SCHOOL_ID);
    void addClass(Class cs);
    void deleteClass(int CLASS_ID);
    void updateClassByTeacher(Class cs);
}
