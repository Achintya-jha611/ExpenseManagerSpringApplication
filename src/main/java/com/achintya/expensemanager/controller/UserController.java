package com.achintya.expensemanager.controller;

import com.achintya.expensemanager.dto.CreateUserRequest;
import com.achintya.expensemanager.dto.UserResponse;
import com.achintya.expensemanager.mapper.UserMapper;
import com.achintya.expensemanager.model.User;
import com.achintya.expensemanager.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponse> addUser(@Valid @RequestBody CreateUserRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }
}
