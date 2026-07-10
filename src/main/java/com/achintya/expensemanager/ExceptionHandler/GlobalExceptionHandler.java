package com.achintya.expensemanager.ExceptionHandler;

import com.achintya.expensemanager.dto.ErrorResponse;
import com.achintya.expensemanager.dto.ValidationError;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

@ControllerAdvice public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception){
    BindingResult bindingResult=exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<ValidationError> validationErrors = new ArrayList<>();
        for(FieldError error: fieldErrors){
            logger.warn("validation failed for the request!printing errors field={} error={}",error.getField(),error.getDefaultMessage());
            ValidationError validationError= new ValidationError(error.getField(),error.getDefaultMessage());
            validationErrors.add(validationError);
        }
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),"Validation Failed",validationErrors);
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
