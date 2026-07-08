package com.achintya.expensemanager.dto;

import org.jspecify.annotations.Nullable;

public class ValidationError {
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String field;
    private String message;

    public ValidationError(String field, @Nullable String defaultMessage) {
        this.field=field;
        this.message=defaultMessage;
    }
}
