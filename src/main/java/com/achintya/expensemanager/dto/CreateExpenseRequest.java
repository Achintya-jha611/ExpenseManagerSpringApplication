package com.achintya.expensemanager.dto;

import com.achintya.expensemanager.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class CreateExpenseRequest {
    @Schema(description = "Expense amount",example="250.50")
    @Positive private float amount;
    @Schema(description = "Expense category ex:- food,shopping,travel etc",example="Food")
    @NotBlank private String category;
    @Schema(description = "Expense description",example="Expense for food ordered online")
    @NotBlank private String description;
    @Schema(description = "Expense date",example="2026-08-15")
    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Expense date cannot be in the future") private LocalDate date;

    @Positive private Integer userId;

    public CreateExpenseRequest(float amount, String category, String description, LocalDate date, Integer userId) {

        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
        this.userId = userId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
