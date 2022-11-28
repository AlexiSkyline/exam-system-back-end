package com.exams.system.app.controller;

import com.exams.system.app.models.ResponseHandler;
import com.exams.system.app.models.domain.Question;
import com.exams.system.app.models.domain.Questionnaire;
import com.exams.system.app.models.response.ResponseBody;
import com.exams.system.app.service.IQuestionService;
import com.exams.system.app.service.IQuestionnaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping( "/question" )
@RequiredArgsConstructor
public class QuestionController {
    private final IQuestionService questionService;
    private final IQuestionnaireService questionnaireService;

    @PostMapping( "/" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<ResponseBody<Question>> saveQuestion( @RequestBody Question question ) {
        return ResponseHandler.responseBuild( CREATED, "Question Created Successfully", this.questionService.add( question )  );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<ResponseBody<Question>> getQuestionById( @PathVariable Long id ) {
        return ResponseHandler.responseBuild( OK, "Requested Question By ID are given here", this.questionService.findById( id ) );
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
        return ResponseHandler.responseBuild( OK, "Request of all the questions of the questionnaire with the ID: " + id, questionnaire );
    }

    @PutMapping( "/" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<ResponseBody<Question>> updateQuestion( @RequestBody Question question ) {
        return ResponseHandler.responseBuild( OK, "Question Update Successfully", this.questionService.update( question ) );
    }

    @DeleteMapping( "/{id}" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<ResponseBody<Question>> deleteQuestion( @PathVariable Long id ) {
        return ResponseHandler.responseBuild( OK, "Category Delete Successfully", this.questionService.deleteById( id ) );
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

        return ResponseHandler.responseBuild( OK, "Questionnaire grading information", response );
    }
}