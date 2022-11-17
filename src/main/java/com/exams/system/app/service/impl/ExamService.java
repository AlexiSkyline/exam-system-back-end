package com.exams.system.app.service.impl;

import com.exams.system.app.models.Exam;
import com.exams.system.app.repository.IExamRepository;
import com.exams.system.app.service.IExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
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
}