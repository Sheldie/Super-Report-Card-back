package com.shezzer.service.impl;

import com.shezzer.mapper.SubjectMapper;
import com.shezzer.pojo.Subject;
import com.shezzer.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectMapper subjectMapper;

    @Override
    public void addSubject(Subject subject) {
        subjectMapper.addSubject(subject);
    }

    @Override
    public void updateSubject(Subject subject) {
        subjectMapper.updateSubject(subject);
    }

    @Override
    public void deleteSubject(int SUBJECT_ID) {
        subjectMapper.deleteSubject(SUBJECT_ID);
    }

    @Override
    public Subject findSubjectById(int SUBJECT_ID) {
        return subjectMapper.findSubjectById(SUBJECT_ID);
    }

    @Override
    public List<Subject> listSubject() {
        return subjectMapper.listSubject();
    }
}
