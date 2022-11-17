package com.exams.system.app.service;

import com.exams.system.app.models.Exam;

import java.util.Set;

public interface IExamService {
    public Exam add( Exam exam );
    public Exam update( Exam exam );
    public Set<Exam> findAll();
    public Exam findById( Long id );
    public void deleteById( Long id );
}