package com.achintya.expensemanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Expense {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;
    private float amount;
    private String category;
    private String description;
    private LocalDate date;
    protected Expense(){};
    public Expense(float amount, String category, String description, LocalDate date){
        //this.id=nextId++;
        if(amount<=0){
            throw new IllegalArgumentException("amount cannot be negative");
        }
        this.amount=amount;
        this.category=category;
        this.description=description;
        this.date=date;
    }
    public Integer getId(){
        return this.id;
    }
    public float getAmount(){
        return this.amount;
    }
    public String getCategory(){
        return this.category;
    }
    public String getDescription(){
        return this.description;
    }
    public LocalDate getDate(){
        return this.date;
    }
    public boolean setAmount(float amount){
        if(amount<=0){
            return false;
        }
        else {
            this.amount = amount;
            return true;
        }
    }
    public void setCategory(String category){
        this.category=category;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public void setDate(LocalDate date){
        this.date=date;
    }
    @Override public String toString(){
        return "Expense{"+
                "id="+id
                +",amount="+amount
                +",category='"+category+ '\'' +
                ",description='"+description+ '\'' +
                ",date='"+date +'\''+
                '}';
    }
}
