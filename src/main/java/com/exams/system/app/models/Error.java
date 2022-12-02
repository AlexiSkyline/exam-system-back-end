package com.exams.system.app.models;

import lombok.Data;

@Data
public class Error {
    private final String message;
    private final String param;
    private final String location;
}