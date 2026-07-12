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
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class ExpenseController {
    private ExpenseService expenseService;
    private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);
    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }
    @GetMapping("/expenses")
    public List<Expense> getAllExpenses(){
        return expenseService.getAllExpenses();
    }
    @PostMapping("/expenses")
    public ResponseEntity<ExpenseResponse> addExpense(@Valid @RequestBody CreateExpenseRequest request){
        Expense expense = ExpenseMapper.toExpense(request);
        Expense createdExpense=expenseService.addExpense(expense);
        logger.info("Expense Successfully Created [id={},amount={} and category={}]",createdExpense.getId(),createdExpense.getAmount(),createdExpense.getCategory());
        return ResponseEntity.status(HttpStatus.CREATED).body(ExpenseMapper.toExpenseResponse(createdExpense));
    }
}
