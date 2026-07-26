package com.achintya.expensemanager.service;

import com.achintya.expensemanager.ExceptionHandler.DuplicateEmailException;
import com.achintya.expensemanager.ExceptionHandler.UserNotFoundException;
import com.achintya.expensemanager.dto.CreateUserRequest;
import com.achintya.expensemanager.dto.UserResponse;
import com.achintya.expensemanager.mapper.UserMapper;
import com.achintya.expensemanager.model.User;
import com.achintya.expensemanager.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public UserResponse createUser(CreateUserRequest request){
            log.info("saving user to db");
            Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
            if(existingUser.isPresent()){
            throw new DuplicateEmailException(request.getEmail());
            }
            User user= UserMapper.toUser(request);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            User userCreated = userRepository.save(user);
            log.info("saved user to db");
            return UserMapper.toUserResponse(userCreated);

    }
    public UserResponse getUserById(Integer id){
        log.info("fetching user data");
        User fetchedUser = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
        //User fetchedUser = user.get();
        log.info("user data fetched successfully");
        return UserMapper.toUserResponse(fetchedUser);
    }

}
