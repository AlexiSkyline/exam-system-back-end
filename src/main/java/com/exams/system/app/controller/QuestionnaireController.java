package com.exams.system.app.controller;

import com.exams.system.app.models.ResponseHandler;
import com.exams.system.app.models.domain.Questionnaire;
import com.exams.system.app.service.IQuestionnaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping( "/questionnaire" )
@RequiredArgsConstructor
public class QuestionnaireController {
    private final IQuestionnaireService questionnaireService;

    @PostMapping( "/" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<?> saveQuestionnaire( @RequestBody @Valid Questionnaire questionnaire ) {
        return ResponseHandler.responseBuild( CREATED, "Questionnaire Created Successfully", this.questionnaireService.add( questionnaire )  );
    }

    @GetMapping( "/" )
    public ResponseEntity<?> getAllQuestionnaires() {
        return ResponseHandler.responseBuild( OK, "Requested All Questionnaire are given here", this.questionnaireService.findAll() );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<?> getQuestionnaireById( @PathVariable Long id ) {
        return ResponseHandler.responseBuild( OK, "Requested Questionnaire By ID are given here", this.questionnaireService.findById( id ) );
    }

    @PutMapping( "/" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<?> updateQuestionnaire( @RequestBody @Valid Questionnaire questionnaire ) {
        return ResponseHandler.responseBuild( OK, "Questionnaire Update Successfully", this.questionnaireService.update( questionnaire ) );
    }

    @DeleteMapping( "/{id}" )
    @PreAuthorize( "hasRole('ADMIN')" )
    public ResponseEntity<?> deleteQuestionnaire( @PathVariable Long id ) {
        return ResponseHandler.responseBuild( OK, "Category Delete Successfully", this.questionnaireService.deleteById( id ) );
    }

    @GetMapping( "/category/{id}" )
    public ResponseEntity<?> getAllQuestionnairesByCategory( @PathVariable Long id ){
        return ResponseHandler.responseBuild( OK, "Request for questionnaires by category ID: " + id, this.questionnaireService.findByCategory( id ) );
    }

    @GetMapping( "/active" )
    public ResponseEntity<?> getAllQuestionnairesActives(){
        return ResponseHandler.responseBuild( OK, "Request for all active questionnaires", this.questionnaireService.findByStatus( true ) );
    }

    @GetMapping( "/category/active/{id}" )
    public ResponseEntity<?> getAllQuestionnairesActivesByCategory( @PathVariable Long id ){
        return ResponseHandler.responseBuild( OK, "Request for all active questionnaires by category ID: " + id,
                this.questionnaireService.findByCategoryIdAndStatus( id, true ) );
    }
}