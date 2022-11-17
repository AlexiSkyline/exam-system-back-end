package com.exams.system.app.service;

import com.exams.system.app.models.Exam;
import com.exams.system.app.models.Question;

import java.util.Set;

public interface IQuestionService {
    public Question add( Question question );
    public Question update( Question question );
    public Set<Question> findAll();
    public Question findById( Long id );
    public Set<Question> findByExam( Exam exam );
    public void deleteById( Long id );
}