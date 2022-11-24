package com.exams.system.app.service.impl;

import com.exams.system.app.models.Category;
import com.exams.system.app.models.Exam;
import com.exams.system.app.repository.IExamRepository;
import com.exams.system.app.service.IExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ExamService implements IExamService {
    private final IExamRepository examRepository;

    @Override
    public Exam add( Exam exam ) {
        return this.examRepository.save( exam );
    }

    @Override
    public Exam update( Exam exam ) {
        return this.examRepository.save( exam );
    }

    @Override
    public Set<Exam> findAll() {
        return new LinkedHashSet<>( this.examRepository.findAll() );
    }

    @Override
    public Exam findById( Long id ) {
        return this.examRepository.findById( id ).get();
    }

    @Override
    public void deleteById( Long id ) {
        this.examRepository.deleteById( id );
    }

    @Override
    public List<Exam> findByCategory( Category category ) {
        return this.examRepository.findByCategory( category );
    }

    @Override
    public List<Exam> findByStatus( Boolean status ) {
        return this.examRepository.findByStatus( status );
    }

    @Override
    public List<Exam> findByCategoryAndStatus( Category category, Boolean status ) {
        return this.examRepository.findByCategoryAndStatus( category, status );
    }
}