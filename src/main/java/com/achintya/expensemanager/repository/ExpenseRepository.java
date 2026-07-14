package com.achintya.expensemanager.repository;

import com.achintya.expensemanager.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
        List<Expense> findByCategoryIgnoreCase(String category);
        List<Expense> findByAmountGreaterThan(float amount);
        List<Expense> findByAmountGreaterThanAndCategory(float amount,String category);
        List<Expense> findByAmountBetween(float startAmount,String endAmount);
        List<Expense> findByDateBetween(float startAmount,String endAmount);
}
