package com.achintya.expensemanager.repository;

import com.achintya.expensemanager.ExceptionHandler.ExpenseNotFoundException;
import com.achintya.expensemanager.dto.CategoryExpenseSummary;
import com.achintya.expensemanager.model.Expense;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Integer> {
        List<Expense> findByCategoryIgnoreCase(String category);
        List<Expense> findByAmountGreaterThan(float amount);
        List<Expense> findByAmountGreaterThanAndCategory(float amount,String category);
        List<Expense> findByAmountBetween(float startAmount,float endAmount);
        List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);
        List<Expense> findByAmountLessThanEqual(float amount);
        List<Expense> findByCategoryIn(List<String>  category);
        List<Expense> findByDescriptionStartingWith(String  description);
        List<Expense> findByDescriptionEndingWith(String  description);
        List<Expense> findTop5ByAmountOrderByAmountDesc(float amount);
        @Query("""
        Select NEW com.achintya.expensemanager.dto.CategoryExpenseSummary(e.category,Sum(e.amount)) from Expense e group by e.category
        """)
        List<CategoryExpenseSummary> findCategoryExpenseSummary();
        @Query(value="""
        select * from expense where category=:category
        """, nativeQuery = true)
        List<Expense> findByCategoryNative(@Param("category") String category);




}
