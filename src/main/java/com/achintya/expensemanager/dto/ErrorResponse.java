package com.achintya.expensemanager.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }

    private LocalDateTime timestamp;
    private String message;
    private List<ValidationError> errors;
    public ErrorResponse(LocalDateTime timestamp, String message, List<ValidationError> errors) {
        this.timestamp = timestamp;
        this.message = message;
        this.errors = errors;
    }



}
