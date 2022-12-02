package com.exams.system.app.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor @Getter
public class ExceptionModel<T> {
    private final String timeStamp;
    private final int httpError;
    private final HttpStatus httpStatus;
    private T information;
}