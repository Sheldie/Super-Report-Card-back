package com.shezzer.service.impl;

import com.shezzer.mapper.ClassMapper;
import com.shezzer.pojo.Class;
import com.shezzer.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    ClassMapper classMapper;

    @Override
    public Class findClassById(int CLASS_ID) {
        return classMapper.findClassById(CLASS_ID);
    }

    @Override
    public List<Class> findAllByClass() {
        return classMapper.findAllByClass();
    }

    @Override
    public List<Class> findClassBySchool(int SCHOOL_ID) {
        return classMapper.findClassBySchool(SCHOOL_ID);
    }

    @Override
    public List<Map> getClassBySchool(int SCHOOL_ID) {
        return classMapper.getClassBySchool(SCHOOL_ID);
    }

    @Override
    public void addClass(Class cs) {
        classMapper.addClass(cs);
    }

    @Override
    public void deleteClass(int CLASS_ID) {
        classMapper.deleteClass(CLASS_ID);
    }

    @Override
    public void updateClassByTeacher(Class cs) {
        classMapper.updateClassByTeacher(cs);
    }
}
