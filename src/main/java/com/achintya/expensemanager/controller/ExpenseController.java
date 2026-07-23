package com.achintya.expensemanager.controller;

import com.achintya.expensemanager.dto.*;
import com.achintya.expensemanager.mapper.ExpenseMapper;
import com.achintya.expensemanager.model.Expense;
import com.achintya.expensemanager.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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
    public Page<Expense> getAllExpenses(@RequestParam(defaultValue = "0")Integer page, @RequestParam(defaultValue = "10")Integer size, @RequestParam(defaultValue = "amount")String sortField,@RequestParam(defaultValue ="asc")String sortOrder){
        return expenseService.getAllExpenses(page,size,sortField,sortOrder);
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
    @PutMapping("/expenses/{id}/amount")
    public ResponseEntity<ExpenseResponse> updateAmountForExpense(@PathVariable Integer id, @RequestParam float amount){
        //Expense updatedExpense = expenseService.updateAmountWithoutSave(id,amount);
        expenseService.checkingTransactionPropagation(id,amount);
        //return ResponseEntity.status(HttpStatus.OK).body(ExpenseMapper.toExpenseResponse(updatedExpense));
        return ResponseEntity.ok().build();

    }
    @PutMapping("/expenses/bulk-update")
    public ResponseEntity<List<ExpenseResponse>> bulkUpdateExpense(@Valid @RequestBody List<BulkUpdateExpense> request){
        //Expense updatedExpense = expenseService.updateAmountWithoutSave(id,amount);
        List<Expense> updatedExpense = expenseService.bulkUpdateExpenseData(request);
        return ResponseEntity.status(HttpStatus.OK).body(ExpenseMapper.toBulkExpenseResponse(updatedExpense));

    }
    @PutMapping("/expense/{id}/rollback-test")
    public ResponseEntity<ExpenseResponse> updateAmountAndDescription(@PathVariable Integer id, @Valid @RequestBody UpdateExpenseRequest request)
    {
        Expense updatedExpense = expenseService.testTransactionRollBack(id, request.getAmount(), request.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(ExpenseMapper.toExpenseResponse(updatedExpense));
        //Expense updatedExpense=
    }
    @DeleteMapping ("/expenses/{id}")
    public boolean deleteExpense(@PathVariable Integer id){
      return  expenseService.deleteExpenseById(id);

    }
}
