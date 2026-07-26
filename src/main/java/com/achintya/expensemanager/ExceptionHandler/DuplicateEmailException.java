package com.achintya.expensemanager.ExceptionHandler;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(String email){
        super("Existing User found with email "+email);
    }
}
