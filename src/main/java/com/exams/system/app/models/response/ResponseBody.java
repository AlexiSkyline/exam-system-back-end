package com.exams.system.app.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor @Getter
public class ResponseBody<T> {
    private final String timeStamp;
    private final int httpCode;
    private final HttpStatus httpStatus;
    private final String message;
    private final T data;
}