package com.achintya.expensemanager.service;
import com.achintya.expensemanager.ExceptionHandler.ExpenseNotFoundException;
import com.achintya.expensemanager.dto.CategoryExpenseSummary;
import com.achintya.expensemanager.model.Expense;
import com.achintya.expensemanager.repository.ExpenseRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
@Service
public class ExpenseService {
    private static final Logger logger = LoggerFactory.getLogger(ExpenseService.class);
    private ExpenseRepository expenseRepository;
    public ExpenseService(ExpenseRepository expenseRepository){
        this.expenseRepository=expenseRepository;
    }
    public void printMenu(){
        System.out.println("===== Expense Manager =====");
        System.out.println("1. Add Expense");
        System.out.println("2. View Expenses");
        System.out.println("3. Delete Expense");
        System.out.println("4. Search Expense");
        System.out.println("5. Update Expense");
        System.out.println("6. Get total expense");
        System.out.println("7. Exit");
        System.out.println("Enter choice");
    }
    public List<Expense> getAllExpenses(){
        return expenseRepository.findAll();
    }
   public Expense addExpense(Expense expense){
       try{
       Expense savedExpenseInDb=expenseRepository.save(expense);
       logger.info("Expense Successfully saved [id={},amount={} and category={}]",savedExpenseInDb.getId(),savedExpenseInDb.getAmount(),savedExpenseInDb.getCategory());
       return savedExpenseInDb;
       }
       catch (Exception e) {
           logger.error("exception occured while adding data to db");
           return null;
       }
   }
    public  List<Expense> viewExpense(){
        System.out.println("fetching the current expense list");
        return expenseRepository.findAll();
    }
    public boolean deleteExpenseById(int id){
        if(expenseRepository.existsById(id)){
        try {
            expenseRepository.deleteById(id);
            logger.info("expense deleted with id={} successfully",id);
            return true;
        }
        catch (Exception e){
            logger.error("could not delete expense with id={} successfully",id);
            return false;
        }}
        else{
            logger.error("id does not exist in the system");
            return false;
        }

    }
    public  Expense getExpenseById(Integer id){
        Optional<Expense> expense = expenseRepository.findById(id);
        Expense actualExpense = expense.get();
        return actualExpense;
    }
    public Expense updateExpenseById(int id, float amount) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException(id));

        expense.setAmount(amount);

        Expense updatedExpense = expenseRepository.save(expense);

        logger.info("Expense updated successfully with id={}", id);

        return updatedExpense;
    }

   public List<Expense> findExpenseByCategory(String category) {
        return expenseRepository.findByCategoryIgnoreCase(category);
    }
    public List<Expense> findExpenseWithAmountGreaterThan(float amount) {
        return expenseRepository.findByAmountGreaterThan(amount);
    }
    public List<Expense> findExpenseWithAmountGreaterThanAndCategory(float amount, String category) {
        return expenseRepository.findByAmountGreaterThanAndCategory(amount,category);
    }
    public List<CategoryExpenseSummary> findCategoryExpenseSummary() {
        return expenseRepository.findCategoryExpenseSummary();
    }
}
