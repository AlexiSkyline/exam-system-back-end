package com.exams.system.app.service.impl;

import com.exams.system.app.models.domain.Question;
import com.exams.system.app.models.domain.Questionnaire;
import com.exams.system.app.repository.IQuestionRepository;
import com.exams.system.app.service.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {
    public final IQuestionRepository questionRepository;

    @Override
    public Question add( Question question ) {
        return this.questionRepository.save( question );
    }

    @Override
    public Question update( Question question ) {
        return this.questionRepository.save( question );
    }

    @Override
    public Set<Question> findAll() {
        return new LinkedHashSet<>( this.questionRepository.findAll() );
    }

    @Override
    public Question findById( Long id ) {
        return this.questionRepository.findById( id ).get();
    }

    @Override
    public Set<Question> findByQuestionnaire( Questionnaire questionnaire ) {
        return this.questionRepository.findByQuestionnaire( questionnaire );
    }

    @Override
    public Question deleteById( Long id ) {
        Question questionFound = this.questionRepository.findById( id ).get();
        this.questionRepository.deleteById( id );
        return questionFound;
    }
}