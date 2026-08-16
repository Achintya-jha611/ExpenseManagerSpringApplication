package com.achintya.expensemanager.config;

import com.achintya.expensemanager.service.CustomUserDetailsService;
import com.achintya.expensemanager.service.JwtService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtService jwtService;

    public JwtAuthenticationFilter(CustomUserDetailsService customUserDetailsService,JwtService jwtService){
        this.customUserDetailsService=customUserDetailsService;
        this.jwtService=jwtService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader==null||!authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
       String token = authHeader.substring(7);
        System.out.println("JWT received");
       String userName= jwtService.extractUserName(token);
       System.out.println("Username from JWT = " + userName);
       UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
       System.out.println("User loaded = " + userDetails.getUsername());
        boolean valid = jwtService.validateToken(token, userDetails);
        System.out.println("Token valid = " + valid);
       /*if(jwtService.validateToken(token, userDetails)){
           UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
           SecurityContextHolder.getContext()
                   .setAuthentication(authentication);
       }
        filterChain.doFilter(request,response);*/
        if(valid){
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

            System.out.println(
                    "Authentication set = " +
                            SecurityContextHolder.getContext().getAuthentication()
            );
        }

        filterChain.doFilter(request,response);



    }
}
