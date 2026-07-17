package com.achintya.expensemanager.dto;

public class CategoryExpenseSummary {
    private String category;

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private double totalAmount;

    public CategoryExpenseSummary(String category,double totalAmount){
        this.category=category;
        this.totalAmount=totalAmount;
    }
}
