package com.shezzer.mapper;

import com.shezzer.pojo.Subject;

import java.util.List;

public interface SubjectMapper {
    Subject findSubjectById(int SUBJECT_ID);
    void addSubject(Subject subject);
    void updateSubject(Subject subject);
    void deleteSubject(int SUBJECT_ID);
    List<Subject> listSubject();
}
