package com.shezzer.mapper;

import com.shezzer.pojo.Class;

import java.util.List;

public interface ClassMapper {
    Class findClassById(int CLASS_ID);
    List<Class> findAllByClass();
}