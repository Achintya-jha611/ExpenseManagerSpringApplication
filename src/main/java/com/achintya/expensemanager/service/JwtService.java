package com.achintya.expensemanager.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtService {
    private static final String SECRET = "mySuperSecretKeyForJwtGeneration123456789";

    public SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }
    public String generateToken(UserDetails userDetails){
    return Jwts.builder().subject(userDetails.getUsername()).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+5*60*1000)).signWith(getSigningKey()).compact();
    }
    public String extractUserName(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }
    public Date ExtractExpiration(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getExpiration();
    }
    public boolean isExpired(String token){
     // Date tokenExpiryTime = ExtractExpiration(token);
      return ExtractExpiration(token).before(new Date());
    }
    public boolean validateToken(String token,UserDetails userDetails){
        return extractUserName(token).equals(userDetails.getUsername())&&!isExpired(token);
    }
}
