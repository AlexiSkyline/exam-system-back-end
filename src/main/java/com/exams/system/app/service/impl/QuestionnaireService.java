package com.exams.system.app.service.impl;

import com.exams.system.app.models.domain.Questionnaire;
import com.exams.system.app.models.exception.RecordNotFoundException;
import com.exams.system.app.repository.ICategoryRepository;
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
    private final ICategoryRepository categoryRepository;

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
        return this.questionnaireRepository.findById( id ).orElseThrow(() -> new RecordNotFoundException( id.toString(), "Questionnaire", "ID" ));
    }

    @Override
    public Questionnaire deleteById( Long id ) {
        Questionnaire questionnaireFound = this.questionnaireRepository.findById( id ).orElseThrow(() -> new RecordNotFoundException( id.toString(), "Questionnaire", "ID" ));
        this.questionnaireRepository.deleteById( id );
        return questionnaireFound;
    }

    @Override
    public List<Questionnaire> findByCategory( Long id ) {
        this.categoryRepository.findById( id ).orElseThrow(() -> new RecordNotFoundException( id.toString(), "Questionnaire", "Category ID" ));
        return this.questionnaireRepository.findByCategoryId( id );
    }

    @Override
    public List<Questionnaire> findByStatus( Boolean status ) {
        return this.questionnaireRepository.findByStatus( status );
    }

    @Override
    public List<Questionnaire> findByCategoryIdAndStatus( Long id, Boolean status ) {
        return this.questionnaireRepository.findByCategoryIdAndStatus( id, status );
    }
}