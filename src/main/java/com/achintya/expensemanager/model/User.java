package com.achintya.expensemanager.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY) private Integer id;
    private String name;
    @Column(unique = true,nullable = false) private String email;
    @Column(nullable = false)private String phoneNumber;
    @Column(nullable = false)private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "user")
    List<Expense> expenses = new ArrayList<>();

    public User(){};
    public User(String name, String email, String phoneNumber, String password, LocalDate dateOfBirth){
        this.name=name;
        this.email=email;
        this.phoneNumber=phoneNumber;
        //this.createdAt=createdAt;
        //this.updatedAt=updatedAt;
        this.password=password;
        this.dateOfBirth=dateOfBirth;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
