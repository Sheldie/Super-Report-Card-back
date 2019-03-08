package com.shezzer.service;

import com.shezzer.pojo.Subject;

import java.util.List;

public interface SubjectService {
    Subject findSubjectById(int SUBJECT_ID);
    List<Subject> listSubject();
}
