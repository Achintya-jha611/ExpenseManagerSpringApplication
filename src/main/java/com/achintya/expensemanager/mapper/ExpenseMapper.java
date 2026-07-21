package com.achintya.expensemanager.mapper;

import com.achintya.expensemanager.dto.CreateExpenseRequest;
import com.achintya.expensemanager.dto.ExpenseResponse;
import com.achintya.expensemanager.model.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpenseMapper {
    public static Expense toExpense(CreateExpenseRequest request){
        return new Expense(request.getAmount(),request.getCategory(),request.getDescription(),request.getDate());
    }

    public static ExpenseResponse toExpenseResponse(Expense createdExpense){
       return new ExpenseResponse(createdExpense.getId(),createdExpense.getAmount(),createdExpense.getCategory(),createdExpense.getDescription(),createdExpense.getDate());
    }
    public static List<ExpenseResponse> toBulkExpenseResponse(List<Expense> bulkResponse){
        List<ExpenseResponse> response = new ArrayList<>();
        for(int i=0;i< bulkResponse.size();i++){
            Expense currentExpense= bulkResponse.get(i);
            ExpenseResponse expenseResponse = new ExpenseResponse(currentExpense.getId(), currentExpense.getAmount(), currentExpense.getCategory(), currentExpense.getDescription(),currentExpense.getDate());
            response.add(expenseResponse);
        }
        return response;
    }
}
