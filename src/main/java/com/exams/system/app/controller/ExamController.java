package com.exams.system.app.controller;

import com.exams.system.app.models.Exam;
import com.exams.system.app.service.IExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/exam" )
@RequiredArgsConstructor
public class ExamController {
    private final IExamService examService;

    @PostMapping( "/" )
    public ResponseEntity<Exam> saveExam( @RequestBody Exam exam ) {
        return ResponseEntity.ok( this.examService.add( exam ) );
    }

    @GetMapping( "/{id}" )
    public Exam getExamById( @PathVariable Long id ) {
        return this.examService.findById( id );
    }

    @GetMapping( "/" )
    public ResponseEntity<?> getAllExams() {
        return ResponseEntity.ok( this.examService.findAll() );
    }

    @PutMapping( "/" )
    public Exam updateExam( @RequestBody Exam exam ) {
        return this.examService.update( exam );
    }

    @DeleteMapping( "/{id}" )
    public void deleteExam( @PathVariable Long id ) {
        this.examService.deleteById( id );
    }
}