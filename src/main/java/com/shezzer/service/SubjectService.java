package com.shezzer.service;

import com.shezzer.pojo.Subject;

import java.util.List;

public interface SubjectService {
    void addSubject(Subject subject);
    void updateSubject(Subject subject);
    void deleteSubject(int SUBJECT_ID);
    Subject findSubjectById(int SUBJECT_ID);
    List<Subject> listSubject();
}
