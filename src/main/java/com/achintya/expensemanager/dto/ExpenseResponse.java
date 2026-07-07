package com.achintya.expensemanager.dto;

public class ExpenseResponse {

    private int id;
    private float amount;
    private String category;
    private String description;
    private String date;


   public ExpenseResponse(int id,float amount,String category,String description, String date){
        this.id=id;
        this.amount=amount;
        this.category=category;
        this.description=description;
        this.date=date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
