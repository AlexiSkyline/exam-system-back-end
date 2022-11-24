package com.exams.system.app.service;

import com.exams.system.app.models.Category;
import com.exams.system.app.models.Exam;

import java.util.List;
import java.util.Set;

public interface IExamService {
    public Exam add( Exam exam );
    public Exam update( Exam exam );
    public Set<Exam> findAll();
    public Exam findById( Long id );
    public void deleteById( Long id );
    List<Exam> findByCategory(Category category );
    List<Exam> findByStatus( Boolean status );
    List<Exam> findByCategoryAndStatus( Category category, Boolean status );
}