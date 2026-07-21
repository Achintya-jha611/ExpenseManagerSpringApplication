package com.achintya.expensemanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class UpdateExpenseRequest {

    @Positive(message = "amount should be positive")
    float amount;
    String description;
    public void setAmount(float amount){
        this.amount=amount;
    }
    public float getAmount(){
        return this.amount;
    }
    public void setDescription(String description){this.description=description;}
    public String getDescription(){return this.description;}

}
