package com.exams.system.app.controller;

import com.exams.system.app.models.Error;
import com.exams.system.app.models.ExceptionModel;
import com.exams.system.app.models.exception.FieldAlreadyUsedException;
import com.exams.system.app.models.exception.RecordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@AllArgsConstructor
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private  final MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid( MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request ) {
        BindingResult result =  ex.getBindingResult();
        List<Error> errors = new ArrayList<>();
        result.getFieldErrors().forEach( error -> {
            String message = messageSource.getMessage( error, Locale.forLanguageTag( "US" ));
            errors.add( new Error( message, error.getField(), "Body" ));
        });
        ExceptionModel<List<Error>> responseBody = new ExceptionModel<>( new Date().toString(), status.value(), status, errors );

        return new ResponseEntity<>( responseBody, status );
    }

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