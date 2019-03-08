package com.shezzer.mapper;

import com.shezzer.pojo.School;

import java.util.List;

public interface SchoolMapper {
    void addSchool(School school);
    School findSchoolByName(String schoolname);
    School findSchoolById(int SCHOOL_ID);
    School findSchoolByUser(int USER_ID);
    School checkSchool(School school);
    void updateSchool(School school);
    List<School> listSchool();
}
