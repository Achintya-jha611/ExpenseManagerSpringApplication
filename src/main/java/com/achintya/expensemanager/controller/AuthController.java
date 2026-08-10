package com.achintya.expensemanager.controller;

import com.achintya.expensemanager.dto.LoginRequest;
import com.achintya.expensemanager.dto.LoginResponse;
import com.achintya.expensemanager.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private LoginService loginService;
    public  AuthController (LoginService loginService){
        this.loginService=loginService;
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        System.out.println("login api was hit");
       return ResponseEntity.status(HttpStatus.OK).body(loginService.login(request));
    }
}
