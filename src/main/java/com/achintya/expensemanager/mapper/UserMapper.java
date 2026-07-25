package com.achintya.expensemanager.mapper;

import com.achintya.expensemanager.dto.CreateUserRequest;
import com.achintya.expensemanager.dto.UserResponse;
import com.achintya.expensemanager.model.User;

public class UserMapper {
    public static User toUser(CreateUserRequest request){
        return new User(request.getName(), request.getEmail(), request.getPhoneNumber(), request.getPassword(), request.getDateOfBirth());
    }
    public static UserResponse toUserResponse(User createdUser){
        return new UserResponse(createdUser.getId(), createdUser.getName(), createdUser.getEmail(), createdUser.getPhoneNumber(),createdUser.getDateOfBirth(),createdUser.getCreatedAt(),createdUser.getUpdatedAt());
    }
}
