package com.achintya.expensemanager.controller;

import com.achintya.expensemanager.dto.CreateUserRequest;
import com.achintya.expensemanager.dto.UserResponse;
import com.achintya.expensemanager.mapper.UserMapper;
import com.achintya.expensemanager.model.User;
import com.achintya.expensemanager.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable @Positive Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }
}
