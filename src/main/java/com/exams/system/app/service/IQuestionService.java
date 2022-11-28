package com.exams.system.app.service;

import com.exams.system.app.models.domain.Question;
import com.exams.system.app.models.domain.Questionnaire;

import java.util.Set;

public interface IQuestionService {
    public Question add( Question question );
    public Question update( Question question );
    public Set<Question> findAll();
    public Question findById( Long id );
    public Set<Question> findByQuestionnaire( Questionnaire questionnaire );
    public Question deleteById( Long id );
}