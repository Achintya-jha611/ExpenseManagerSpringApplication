package com.achintya.expensemanager.mapper;

import com.achintya.expensemanager.dto.LoginResponse;
import com.achintya.expensemanager.dto.UserResponse;
import com.achintya.expensemanager.model.User;

public class LoginMapper {
    public static LoginResponse toLoginResponse(String token){
        return new LoginResponse(token);
    }
}
