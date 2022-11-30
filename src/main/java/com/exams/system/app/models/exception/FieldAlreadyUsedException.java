package com.exams.system.app.models.exception;

import lombok.Getter;

@Getter
public class FieldAlreadyUsedException extends RuntimeException {
    private final String fieldName;
    private final String columnName;

    public FieldAlreadyUsedException( String fieldName, String columnName ) {
        this.fieldName = fieldName;
        this.columnName = columnName;
    }

    @Override
    public String getMessage() {
        return String.format( "Field '%s' is already being used with another %s.", this.fieldName, this.columnName );
    }
}
