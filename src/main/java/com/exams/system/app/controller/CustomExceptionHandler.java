package com.exams.system.app.controller;

import com.exams.system.app.models.Error;
import com.exams.system.app.models.ExceptionModel;
import com.exams.system.app.models.exception.FieldAlreadyUsedException;
import com.exams.system.app.models.exception.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@AllArgsConstructor
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler( value = { FieldAlreadyUsedException.class } )
    protected ResponseEntity<ExceptionModel<Error>> anyFieldAlreadyUsedException( FieldAlreadyUsedException exception ) {
        Error error = new Error( exception.getMessage(), exception.getFieldName(), "Body" );
        ExceptionModel<Error> responseBody = new ExceptionModel<>( new Date().toString(), CONFLICT.value(), CONFLICT, error );
        return new ResponseEntity<>( responseBody, CONFLICT );
    }

    @ExceptionHandler( value = { RecordNotFoundException.class } )
    protected ResponseEntity<ExceptionModel<Error>> recordNotFoundException( RecordNotFoundException exception ) {
        Error error = new Error( exception.getMessage(), exception.getFieldName(), "PathVariable" );
        ExceptionModel<Error> responseBody = new ExceptionModel<>( new Date().toString(), NOT_FOUND.value(), NOT_FOUND, error );
        return new ResponseEntity<>( responseBody, NOT_FOUND );
    }
}