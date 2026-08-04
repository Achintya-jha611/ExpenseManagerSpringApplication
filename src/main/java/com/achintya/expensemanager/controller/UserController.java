package com.achintya.expensemanager.controller;

import com.achintya.expensemanager.dto.CreateUserRequest;
import com.achintya.expensemanager.dto.ExpenseResponse;
import com.achintya.expensemanager.dto.UpdateUserRequest;
import com.achintya.expensemanager.dto.UserResponse;
import com.achintya.expensemanager.mapper.UserMapper;
import com.achintya.expensemanager.model.User;
import com.achintya.expensemanager.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateUserRequest request , @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(request,id));
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/users/{id}/expenses") public ResponseEntity<List<ExpenseResponse>> getUserExpense(@PathVariable @Positive Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserExpense(id));
    }
}
