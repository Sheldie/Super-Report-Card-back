package com.shezzer.service;

import com.shezzer.pojo.School;

import java.util.List;

public interface SchoolService {
    boolean addSchool(School school);
    School checkSchoolname(String schoolname);
    School findSchoolById(int SCHOOL_ID);
    School findSchoolByUser(int USER_ID);
    School checkSchool(School school);
    boolean updateSchool(School school);
    List<School> listSchool();
}
