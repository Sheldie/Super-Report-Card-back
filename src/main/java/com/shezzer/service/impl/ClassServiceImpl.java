package com.shezzer.service.impl;

import com.shezzer.mapper.ClassMapper;
import com.shezzer.pojo.Class;
import com.shezzer.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
