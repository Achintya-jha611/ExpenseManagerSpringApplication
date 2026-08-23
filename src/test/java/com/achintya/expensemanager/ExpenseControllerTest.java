package com.achintya.expensemanager;

import com.achintya.expensemanager.controller.ExpenseController;
import com.achintya.expensemanager.model.Expense;
import com.achintya.expensemanager.service.CustomUserDetailsService;
import com.achintya.expensemanager.service.ExpenseService;
import com.achintya.expensemanager.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ExpenseController.class)
public class ExpenseControllerTest {
    @Autowired private MockMvc mockMvc;
    @MockitoBean private ExpenseService expenseService;
    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;
    @MockitoBean
    private JwtService jwtService;

    @Test
    void shouldReturnExpensesByCategory() throws Exception {
        Expense expense = new Expense(
                500,
                "food",
                "lunch",
                LocalDate.now()
        );

        List<Expense> expenses = List.of(expense);
        when(expenseService.findExpenseByCategoryNative("food")).thenReturn(expenses);

        mockMvc.perform(get("/expenses/category").param("category","food")).andExpect(status().isOk()).andExpect(jsonPath("$[0].amount").value(500))
                .andExpect(jsonPath("$[0].category").value("food"))
                .andExpect(jsonPath("$[0].description").value("lunch"));//$ → root,   [0]→ first element of list ,amount → amount field

        verify(expenseService).findExpenseByCategoryNative("food");//checks if call to service with the passed param happened

        /*
        when(...)
         ↓
    Mock service ko response configure karna
     MockMvc.perform(...)
          ↓
    HTTP request simulate karna

    andExpect(...)
        ↓
    HTTP response verify karna

    verify(...)
        ↓
    Controller → Service interaction verify karna
         */
    }
}
