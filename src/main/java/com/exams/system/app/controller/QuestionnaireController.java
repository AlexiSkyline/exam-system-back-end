package com.exams.system.app.controller;

import com.exams.system.app.models.Category;
import com.exams.system.app.models.Questionnaire;
import com.exams.system.app.service.IQuestionnaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/questionnaire" )
@RequiredArgsConstructor
public class QuestionnaireController {
    private final IQuestionnaireService questionnaireService;

    @PostMapping( "/" )
    public ResponseEntity<Questionnaire> saveQuestionnaire( @RequestBody Questionnaire questionnaire ) {
        return ResponseEntity.ok( this.questionnaireService.add( questionnaire ) );
    }

    @GetMapping( "/{id}" )
    public Questionnaire getQuestionnaireById( @PathVariable Long id ) {
        return this.questionnaireService.findById( id );
    }

    @GetMapping( "/" )
    public ResponseEntity<?> getAllQuestionnaires() {
        return ResponseEntity.ok( this.questionnaireService.findAll() );
    }

    @PutMapping( "/" )
    public Questionnaire updateQuestionnaire( @RequestBody Questionnaire questionnaire ) {
        return this.questionnaireService.update( questionnaire );
    }

    @DeleteMapping( "/{id}" )
    public void deleteQuestionnaire( @PathVariable Long id ) {
        this.questionnaireService.deleteById( id );
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getAllQuestionnairesByCategory( @PathVariable Long id ){
        Category category = new Category();
        category.setId( id );
        return ResponseEntity.ok( this.questionnaireService.findByCategory( category ) );
    }

    @GetMapping("/active")
    public ResponseEntity<?> getAllQuestionnairesActives(){
        return ResponseEntity.ok( this.questionnaireService.findByStatus( true ) );
    }

    @GetMapping("/category/active/{id}")
    public ResponseEntity<?> getAllQuestionnairesActivesByCategory( @PathVariable Long id ){
        Category category = new Category();
        category.setId( id );
        return ResponseEntity.ok( this.questionnaireService.findByCategoryAndStatus( category, true ) );
    }
}