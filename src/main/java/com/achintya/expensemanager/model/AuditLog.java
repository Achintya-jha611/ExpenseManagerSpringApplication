package com.achintya.expensemanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity

public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)Integer id;
    LocalDateTime createdAt;
    String action;
     protected AuditLog(){}

    public AuditLog(String action,LocalDateTime createdAt){
        this.action = action;
        this.createdAt = createdAt;
    };
    public Integer getId() {
        return id;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
