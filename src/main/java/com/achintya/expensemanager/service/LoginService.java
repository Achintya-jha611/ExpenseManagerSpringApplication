package com.achintya.expensemanager.service;

import com.achintya.expensemanager.dto.LoginRequest;
import com.achintya.expensemanager.dto.LoginResponse;
import com.achintya.expensemanager.mapper.LoginMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public LoginService(AuthenticationManager authenticationManager,JwtService jwtService){
        this.authenticationManager=authenticationManager;
        this.jwtService=jwtService;
    }
    public LoginResponse login(LoginRequest loginRequest){
        System.out.println("Before authenticate");
     Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassWord()));
        System.out.println("Before authenticate");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("Raw Password = " + loginRequest.getPassWord());
        String token = jwtService.generateToken(userDetails);
        return LoginMapper.toLoginResponse(token);




        /*Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getEmail(),
                                loginRequest.getPassWord()
                        )
                );*/


    }
}
