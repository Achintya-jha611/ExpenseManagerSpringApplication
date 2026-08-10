package com.achintya.expensemanager.ExceptionHandler;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Integer id){
        super("User not found with id "+id);
    }
    public UserNotFoundException(String name){
        super("User not found with name "+name);
    }
}
