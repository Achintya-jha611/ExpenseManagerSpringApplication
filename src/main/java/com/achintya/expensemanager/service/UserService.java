package com.achintya.expensemanager.service;

import com.achintya.expensemanager.dto.CreateUserRequest;
import com.achintya.expensemanager.dto.UserResponse;
import com.achintya.expensemanager.mapper.UserMapper;
import com.achintya.expensemanager.model.User;
import com.achintya.expensemanager.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public UserResponse createUser(CreateUserRequest request){
            log.info("saving user to db");
            User user= UserMapper.toUser(request);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            User userCreated = userRepository.save(user);
            log.info("saved user to db");
            return UserMapper.toUserResponse(userCreated);

    }

}
