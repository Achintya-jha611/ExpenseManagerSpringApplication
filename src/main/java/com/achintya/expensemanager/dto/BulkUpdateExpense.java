package com.achintya.expensemanager.dto;

public class BulkUpdateExpense {
    float amount;
    private Integer id;
    public float getAmount(){
        return this.amount;
    }
    public void setAmount(float amount){
        this.amount = amount;
    }
    public Integer getId(){
        return this.id;
    }
}
