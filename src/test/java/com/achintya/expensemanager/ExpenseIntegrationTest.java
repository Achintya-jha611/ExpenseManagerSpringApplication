package com.achintya.expensemanager;
import com.achintya.expensemanager.model.Expense;
import com.achintya.expensemanager.model.User;
import com.achintya.expensemanager.repository.ExpenseRepository;
import com.achintya.expensemanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.time.LocalDate;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class ExpenseIntegrationTest {
    @Container
    @ServiceConnection
    static MySQLContainer<?> mysql =
            new MySQLContainer<>("mysql:8.0");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldCreateExpenseAndSaveItInDatabase() throws Exception {
        User user = new User(
                "Achintya",
                "achintya@test.com",
                "1234567890",
                "password",
                LocalDate.of(2000, 1, 1)
        );

        User savedUser = userRepository.save(user);

        String requestJson = """
        {
            "amount": 500,
            "category": "food",
            "description": "lunch",
            "date": "2026-08-27",
            "userId": %d
        }
        """.formatted(savedUser.getId());

        mockMvc.perform(
                        post("/expenses")
                                .with(user("achintya@test.com"))
                                .with(csrf())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson)
                )
                .andExpect(status().isCreated());
        assertThat(expenseRepository.count()).isEqualTo(1);

        Expense savedExpense = expenseRepository.findAll().get(0);

        assertThat(savedExpense.getAmount()).isEqualTo(500);
        assertThat(savedExpense.getCategory()).isEqualTo("food");
        assertThat(savedExpense.getDescription()).isEqualTo("lunch");
        assertThat(savedExpense.getUser().getId())
                .isEqualTo(savedUser.getId());
    }
}
