package com.achintya.expensemanager.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank String email;
    @NotBlank String passWord;
    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
