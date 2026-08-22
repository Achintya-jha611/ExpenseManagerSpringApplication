package com.achintya.expensemanager;

import com.achintya.expensemanager.ExceptionHandler.ExpenseNotFoundException;
import com.achintya.expensemanager.model.AuditLog;
import com.achintya.expensemanager.model.Expense;
import com.achintya.expensemanager.repository.ExpenseRepository;
import com.achintya.expensemanager.repository.UserRepository;
import com.achintya.expensemanager.service.AuditService;
import com.achintya.expensemanager.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {
    @Mock
    private  ExpenseRepository expenseRepository;
    @Mock
    private  AuditService auditService;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ExpenseService expenseService;

    private Expense expense;

    @BeforeEach
    void setUp(){
         expense = new Expense(500,"cab","cab expense", LocalDate.now());
    }
    @Test
    void shouldReturnExpenseId() {

        //ExpenseService expenseService= new ExpenseService(expenseRepository,auditService,userRepository);

        when(expenseRepository.findById(1)).thenReturn(Optional.of(expense));
        Expense result = expenseService.getExpenseById(1);
        assertEquals(expense,result);
        verify(expenseRepository).findById(1);
    }
    @Test void shouldReturnExpenseNotFound(){
        when(expenseRepository.findById(1)).thenReturn(Optional.empty());
        ExpenseNotFoundException exception = assertThrows
                ( ExpenseNotFoundException.class, () -> expenseService.getExpenseById(1));
        assertEquals( "Expense not found with id:1", exception.getMessage() ); /*assertThrows(ExpenseNotFoundException.class, ()->expenseService.getExpenseById(1)//this asserts expected data );*/
        verify(expenseRepository).findById(1);//this asserts whether call was made to the method or not }
    }
    @Test
    void shouldDeleteExpenseSuccessfully(){
       when(expenseRepository.existsById(1)).thenReturn(true);
       boolean result = expenseService.deleteExpenseById(1);
       assertEquals(result,true);
       verify(expenseRepository).deleteById(1);
    }
    @Test
    void shouldNotDelete(){
        when(expenseRepository.existsById(5)).thenReturn(false);
        boolean result = expenseService.deleteExpenseById(5);
        assertFalse(result);
        verify(expenseRepository,never()).deleteById(5);
    }
}
