package com.achintya.expensemanager.ExceptionHandler;

import com.achintya.expensemanager.model.Expense;

public class ExpenseNotFoundException extends RuntimeException{
   public  ExpenseNotFoundException(Integer id){
        super("Expense not found with id:"+id);
    }
}
