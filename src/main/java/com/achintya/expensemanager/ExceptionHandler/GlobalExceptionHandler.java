package com.achintya.expensemanager.ExceptionHandler;

import com.achintya.expensemanager.dto.ErrorResponse;
import com.achintya.expensemanager.dto.ValidationError;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception){
    BindingResult bindingResult=exception.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<ValidationError> validationErrors = new ArrayList<>();
        for(FieldError error: fieldErrors){
            ValidationError validationError= new ValidationError(error.getField(),error.getDefaultMessage());
            validationErrors.add(validationError);
        }
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),"Validation Failed",validationErrors);
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
