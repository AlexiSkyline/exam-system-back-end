package com.exams.system.app.service;

import com.exams.system.app.models.domain.Category;
import com.exams.system.app.models.domain.Questionnaire;

import java.util.List;
import java.util.Set;

public interface IQuestionnaireService {
    public Questionnaire add( Questionnaire questionnaire );
    public Questionnaire update( Questionnaire questionnaire );
    public Set<Questionnaire> findAll();
    public Questionnaire findById( Long id );
    public Questionnaire deleteById( Long id );
    List<Questionnaire> findByCategory(Category category );
    List<Questionnaire> findByStatus( Boolean status );
    List<Questionnaire> findByCategoryAndStatus( Category category, Boolean status );
}