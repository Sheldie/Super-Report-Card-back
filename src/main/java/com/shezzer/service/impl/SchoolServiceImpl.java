package com.shezzer.service.impl;

import com.shezzer.mapper.SchoolMapper;
import com.shezzer.pojo.School;
import com.shezzer.pojo.User;
import com.shezzer.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    SchoolMapper schoolMapper;

    @Override
    public boolean addSchool(School school) {
        School temp = schoolMapper.findSchoolByName(school.getSCHOOL_NAME());
        if(temp != null)
            return false;
        schoolMapper.addSchool(school);
        return true;
    }

    @Override
    public School checkSchoolname(String schoolname) {
        School school = schoolMapper.findSchoolByName(schoolname);
        if(school != null)
            return school;
        else
            return null;
    }

    @Override
    public School findSchoolById(int SCHOOL_ID) {
        School school = schoolMapper.findSchoolById(SCHOOL_ID);
        if(school != null)
            return school;
        else
            return null;
    }

    @Override
    public School findSchoolByUser(User user) {
        School school = schoolMapper.findSchoolByUser(user);
        if(school != null)
            return school;
        else
            return null;
    }

    @Override
    public School checkSchool(School school) {
        return schoolMapper.checkSchool(school);
    }

    @Override
    public boolean updateSchool(School school) {
        School temp = schoolMapper.findSchoolById(school.getSCHOOL_ID());
        if(temp == null)
            return false;
        schoolMapper.updateSchool(school);
        return true;
    }

    @Override
    public List<School> listSchool() {
        return schoolMapper.listSchool();
    }
}
