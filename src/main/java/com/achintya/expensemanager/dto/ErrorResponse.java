package com.achintya.expensemanager.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private List<ValidationError> errors;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    private HttpStatus status;

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

    public ErrorResponse(LocalDateTime timestamp, String message, List<ValidationError> errors,HttpStatus status) {
        this.timestamp = timestamp;
        this.message = message;
        this.errors = errors;
        this.status = status;
    }
    public ErrorResponse(LocalDateTime timestamp, String message, HttpStatus status) {
        this.message = message;
        this.timestamp = timestamp;
        this.status = status;
    }




}
