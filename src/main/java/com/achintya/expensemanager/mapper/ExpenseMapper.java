package com.achintya.expensemanager.mapper;

import com.achintya.expensemanager.dto.CreateExpenseRequest;
import com.achintya.expensemanager.dto.ExpenseResponse;
import com.achintya.expensemanager.model.Expense;

public class ExpenseMapper {
    public static Expense toExpense(CreateExpenseRequest request){
        return new Expense(request.getAmount(),request.getCategory(),request.getDescription(),request.getDate());
    }

    public static ExpenseResponse toExpenseResponse(Expense createdExpense){
       return new ExpenseResponse(createdExpense.getId(),createdExpense.getAmount(),createdExpense.getCategory(),createdExpense.getDescription(),createdExpense.getDate());
    }
}
