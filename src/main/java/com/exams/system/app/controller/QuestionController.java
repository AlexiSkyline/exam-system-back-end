package com.exams.system.app.controller;

import com.exams.system.app.models.Question;
import com.exams.system.app.models.Questionnaire;
import com.exams.system.app.service.IQuestionService;
import com.exams.system.app.service.IQuestionnaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping( "/question" )
@RequiredArgsConstructor
public class QuestionController {
    private final IQuestionService questionService;
    private final IQuestionnaireService questionnaireService;

    @PostMapping( "/" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<Question> saveQuestion( @RequestBody Question question ) {
        return ResponseEntity.ok( this.questionService.add( question ) );
    }

    @PutMapping( "/" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<Question> updateQuestion( @RequestBody Question question ) {
        return ResponseEntity.ok( this.questionService.update( question ) );
    }

    @GetMapping( "/questionnaire/{id}" )
    public ResponseEntity<?> getQuestionsByQuestionnaire( @PathVariable Long id ) {
        Questionnaire questionnaire = this.questionnaireService.findById( id );
        Set<Question> questions = questionnaire.getQuestions();

        List<Question> questionnaires = new ArrayList<>( questions );
        if( questionnaires.size() > questionnaire.getNumberQuestions() ) {
            questionnaires = questionnaires.subList( 0, questionnaire.getNumberQuestions() + 1 );
        }

        Collections.shuffle( questionnaires );
        return ResponseEntity.ok( questionnaires );
    }

    @GetMapping( "/{id}" )
    public Question getQuestionById( @PathVariable Long id ) {
        return this.questionService.findById( id );
    }

    @DeleteMapping( "/{id}" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public void deleteQuestion( @PathVariable Long id ) {
        this.questionService.deleteById( id );
    }

    @PostMapping( "/mark-questionnaire" )
    public ResponseEntity<?> markQuestionnaire( @RequestBody List<Question> questions ) {
        double maximumPoints = 0;
        Integer correctAnswers = 0;
        Integer attempts = 0;

        for( Question question: questions ) {
            Question current_question = this.questionService.findById( question.getId() );
            if( current_question.getAnswer().equals( question.getUserAnswer() ) ) {
                correctAnswers++;
                double points = (double)questions.get(0).getQuestionnaire().getMaxPoints()/questions.size();
                maximumPoints += points;
            }

            if( current_question.getUserAnswer() != null ) {
                attempts++;
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put( "maximumPoints", maximumPoints );
        response.put( "correctAnswers", correctAnswers );
        response.put( "attempts", attempts );

        return ResponseEntity.ok( response );
    }
}