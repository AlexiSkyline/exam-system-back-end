package com.exams.system.app.controller;

import com.exams.system.app.models.Exam;
import com.exams.system.app.models.Question;
import com.exams.system.app.service.IExamService;
import com.exams.system.app.service.IQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping( "/question" )
@RequiredArgsConstructor
public class QuestionController {
    private final IQuestionService questionService;
    private final IExamService examService;

    @PostMapping( "/" )
    public ResponseEntity<Question> saveQuestion( @RequestBody Question question ) {
        return ResponseEntity.ok( this.questionService.add( question ) );
    }

    @PutMapping( "/" )
    public ResponseEntity<Question> updateQuestion( @RequestBody Question question ) {
        return ResponseEntity.ok( this.questionService.update( question ) );
    }

    @GetMapping( "/exam/{id}" )
    public ResponseEntity<?> getQuestionsByExam( @PathVariable Long id ) {
        Exam exam = this.examService.findById( id );
        Set<Question> questions = exam.getQuestions();

        List<Question> exams = new ArrayList<>( questions );
        if( exams.size() > exam.getNumberQuestions() ) {
            exams = exams.subList( 0, exam.getNumberQuestions() + 1 );
        }

        Collections.shuffle( exams );
        return ResponseEntity.ok( exams );
    }

    @GetMapping( "/{id}" )
    public Question getQuestionById( @PathVariable Long id ) {
        return this.questionService.findById( id );
    }

    @DeleteMapping( "/{id}" )
    public void deleteQuestion( @PathVariable Long id ) {
        this.questionService.deleteById( id );
    }
}