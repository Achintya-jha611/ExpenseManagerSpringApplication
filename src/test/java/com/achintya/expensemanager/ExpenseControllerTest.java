package com.achintya.expensemanager;

import com.achintya.expensemanager.controller.ExpenseController;
import com.achintya.expensemanager.dto.CreateExpenseRequest;
import com.achintya.expensemanager.model.Expense;
import com.achintya.expensemanager.model.User;
import com.achintya.expensemanager.repository.UserRepository;
import com.achintya.expensemanager.service.CustomUserDetailsService;
import com.achintya.expensemanager.service.ExpenseService;
import com.achintya.expensemanager.service.JwtService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.http.MediaType;

import javax.swing.text.html.Option;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ExpenseController.class)
public class ExpenseControllerTest {
    @Autowired private MockMvc mockMvc;
    @Mock
    private UserRepository userRepository;
    @MockitoBean private ExpenseService expenseService;
    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;
    @MockitoBean
    private JwtService jwtService;
    private User user;
    private Expense expense;

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
    @Test
    void shouldReturnInvalidPayload() throws Exception {
        String invalidRequest = """
            {
                "amount": -100,
                "category": "food",
                "description": "lunch",
                "date": "2026-08-20"
            }
            """;

        mockMvc.perform(
                        post("/expenses")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(invalidRequest) //making request through mvc
                )
                .andExpect(status().isBadRequest());//return bad request in this case

        verify(expenseService, never()).addExpense(any());//verifying that expense service never calss add expense
    }
    @Test
    void shouldCallServiceDuringExpenseCreation() throws Exception {
      String validRequest = """
            {
                "amount": 100,
                "category": "food",
                "description": "lunch",
                "date": "2026-08-20"
            }
            """;
        Expense savedExpense = new Expense(
                100,
                "food",
                "lunch",
                LocalDate.of(2026, 8, 20)
        );
        savedExpense.setId(1);
       when(expenseService.addExpense(any(CreateExpenseRequest.class))).thenReturn(savedExpense);
        mockMvc.perform(
                        post("/expenses")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(validRequest)//making request through mvc
                )
                .andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.amount").value(100)).andExpect(jsonPath("$.category").value("food")).andExpect(jsonPath("$.description").value("lunch")).andExpect(jsonPath("$.date").value("2026-08-20"));//return success in this case

        verify(expenseService).addExpense(any(CreateExpenseRequest.class));


    }
}
