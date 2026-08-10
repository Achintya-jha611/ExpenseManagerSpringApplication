package com.achintya.expensemanager.service;

import com.achintya.expensemanager.ExceptionHandler.UserNotFoundException;
import com.achintya.expensemanager.model.User;
import com.achintya.expensemanager.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException(username));
        System.out.println("Stored Password = " + user.getPassword());
        return user;
        //return null;
    }
}
