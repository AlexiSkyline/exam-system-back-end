package com.exams.system.app.models.exception;

import lombok.Getter;

public class RecordNotFoundException extends RuntimeException {
    private final String value;
    private final String tableName;
    @Getter
    private final String fieldName;

    public RecordNotFoundException( String value, String tableName, String fieldName ) {
        this.value = value;
        this.tableName = tableName;
        this.fieldName = fieldName;
    }

    @Override
    public String getMessage() {
        return String.format( "Not Found Any %s With The %s:'%s'", this.tableName, this.fieldName, this.value );
    }
}