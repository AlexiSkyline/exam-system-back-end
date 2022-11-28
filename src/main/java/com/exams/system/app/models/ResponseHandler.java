package com.exams.system.app.models;

import com.exams.system.app.models.response.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class ResponseHandler {
    public static <T> ResponseEntity<ResponseBody<T>> responseBuild( HttpStatus httpStatus, String message, T data ) {
        ResponseBody<T> responseBody = new ResponseBody<>( new Date().toString(), httpStatus.value(), httpStatus, message, data );
        return new ResponseEntity<>( responseBody, httpStatus );
    }
}