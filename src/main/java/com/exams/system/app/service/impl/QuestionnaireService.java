package com.exams.system.app.service.impl;

import com.exams.system.app.models.domain.Category;
import com.exams.system.app.models.domain.Questionnaire;
import com.exams.system.app.repository.IQuestionnaireRepository;
import com.exams.system.app.service.IQuestionnaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuestionnaireService implements IQuestionnaireService {
    private final IQuestionnaireRepository questionnaireRepository;

    @Override
    public Questionnaire add( Questionnaire questionnaire ) {
        return this.questionnaireRepository.save( questionnaire );
    }

    @Override
    public Questionnaire update( Questionnaire questionnaire ) {
        return this.questionnaireRepository.save( questionnaire );
    }

    @Override
    public Set<Questionnaire> findAll() {
        return new LinkedHashSet<>( this.questionnaireRepository.findAll() );
    }

    @Override
    public Questionnaire findById( Long id ) {
        return this.questionnaireRepository.findById( id ).get();
    }

    @Override
    public Questionnaire deleteById( Long id ) {
        Questionnaire questionnaireFound = this.questionnaireRepository.findById( id ).get();
        this.questionnaireRepository.deleteById( id );

        return questionnaireFound;
    }

    @Override
    public List<Questionnaire> findByCategory( Category category ) {
        return this.questionnaireRepository.findByCategory( category );
    }

    @Override
    public List<Questionnaire> findByStatus( Boolean status ) {
        return this.questionnaireRepository.findByStatus( status );
    }

    @Override
    public List<Questionnaire> findByCategoryAndStatus( Category category, Boolean status ) {
        return this.questionnaireRepository.findByCategoryAndStatus( category, status );
    }
}