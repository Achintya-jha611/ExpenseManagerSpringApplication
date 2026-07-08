package com.achintya.expensemanager.controller;

import com.achintya.expensemanager.dto.CreateExpenseRequest;
import com.achintya.expensemanager.dto.ExpenseResponse;
import com.achintya.expensemanager.mapper.ExpenseMapper;
import com.achintya.expensemanager.model.Expense;
import com.achintya.expensemanager.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ExpenseController {
    private ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }
    @GetMapping("/expenses")
    public ArrayList<Expense> getAllExpenses(){
        return expenseService.getAllExpenses();
    }
    @PostMapping("/expenses")
    public ResponseEntity<ExpenseResponse> addExpense(@Valid @RequestBody CreateExpenseRequest request){
        Expense expense = ExpenseMapper.toExpense(request);
        //Expense expense = new Expense(request.getAmount(),request.getCategory(),request.getDescription(),request.getDate());
        Expense createdExpense=expenseService.addExpense(expense);
        //return new ExpenseResponse(createdExpense.getId(),createdExpense.getAmount(),createdExpense.getCategory(),createdExpense.getDescription(),createdExpense.getDate());
        return ResponseEntity.status(HttpStatus.CREATED).body(ExpenseMapper.toExpenseResponse(createdExpense));
    }
}
