package com.achintya.expensemanager.controller;

import com.achintya.expensemanager.dto.CategoryExpenseSummary;
import com.achintya.expensemanager.dto.CreateExpenseRequest;
import com.achintya.expensemanager.dto.ExpenseResponse;
import com.achintya.expensemanager.dto.UpdateExpenseRequest;
import com.achintya.expensemanager.mapper.ExpenseMapper;
import com.achintya.expensemanager.model.Expense;
import com.achintya.expensemanager.service.ExpenseService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/expenses/category-summary")
    public List<CategoryExpenseSummary> getAllTotalByCategory(){
        return expenseService.findCategoryExpenseSummary();
    }
    @GetMapping("/expenses/category")
    public List<Expense> getAllExpenseByCategory(@RequestParam String category){
        return expenseService.findExpenseByCategoryNative(category);
    }
    @PostMapping("/expenses")
    public ResponseEntity<ExpenseResponse> addExpense(@Valid @RequestBody CreateExpenseRequest request){
        Expense expense = ExpenseMapper.toExpense(request);
        Expense createdExpense=expenseService.addExpense(expense);
        logger.info("Expense Successfully Created [id={},amount={} and category={}]",createdExpense.getId(),createdExpense.getAmount(),createdExpense.getCategory());
        return ResponseEntity.status(HttpStatus.CREATED).body(ExpenseMapper.toExpenseResponse(createdExpense));
    }
    @PutMapping ("/expenses/{id}")
    public ResponseEntity<ExpenseResponse> updateExpense(@PathVariable Integer id,@Valid @RequestBody UpdateExpenseRequest request){
        Expense updatedExpense = expenseService.updateExpenseById(id,request.getAmount());
        return ResponseEntity.ok(
                ExpenseMapper.toExpenseResponse(updatedExpense));
       /* if (updatedExpense!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(ExpenseMapper.toExpenseResponse(updatedExpense));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExpenseMapper.toExpenseResponse(updatedExpense));
        }*/
    }
    @DeleteMapping ("/expenses/{id}")
    public boolean deleteExpense(@PathVariable Integer id){
      return  expenseService.deleteExpenseById(id);

    }
}
