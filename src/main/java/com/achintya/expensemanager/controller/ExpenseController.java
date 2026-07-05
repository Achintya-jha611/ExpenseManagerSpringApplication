package com.achintya.expensemanager.controller;

import com.achintya.expensemanager.model.Expense;
import com.achintya.expensemanager.service.ExpenseService;
import org.springframework.web.bind.annotation.GetMapping;
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
}
