package com.example.create_web_app.auth.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.Password;

/**
 * The AuthService class is responsible for generating and validating JWT
 * tokens.
 */
@Component
public class AuthService {

    private Password key = Keys.password(System.getenv("SECRET_KEY").toCharArray());

    /**
     * The createToken method is used to create a JWT token.
     * 
     * @param claims   the claims to be added to the token
     * @param username the username of the user
     * @return the token
     */
    private String createToken(Map<String, Object> claims, String username) {

        // Set the expiration time of the token
        long expire = Long.parseLong(System.getenv("ACCESS_TOKEN_EXPIRE_MINUTES")) * 1000 * 60;
        Date access_token_expires = new Date(System.currentTimeMillis() + expire);

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(access_token_expires)
                .signWith(this.key)
                .compact();
    }

    /**
     * The extractAllClaims method is used to extract all the claims from the token.
     * 
     * @param token the token
     * @return the claims
     */
    private Jws<Claims> extractAllClaims(String token) {
        try {
            return Jwts
                    .parser()
                    .clockSkewSeconds(180)
                    .verifyWith(this.key)
                    .build()
                    .parseSignedClaims(token);
        } catch (JwtException exception) {
            throw new JwtException("Invalid Token");
        }
    }

    /**
     * The isTokenExpired method is used to check if the token is expired.
     * 
     * @param token the token
     * @return true if the token is expired, false otherwise
     */
    private Boolean isTokenExpired(String token) {
        final Claims claims = extractAllClaims(token).getPayload();
        return claims.getExpiration().before(new Date());
    }

    /**
     * The extractUsername method is used to extract the username from the token.
     * 
     * @param token the token
     * @return the username
     */
    public String extractUsername(String token) {
        final Claims claims = extractAllClaims(token).getPayload();
        return claims.getSubject();
    }

    /**
     * The extractScopes method is used to extract the scopes from the token.
     * 
     * @param token the token
     * @return the scopes
     */
    public String extractScopes(String token) {
        final Claims claims = extractAllClaims(token).getPayload();
        return claims.get("scopes").toString();
    }

    /**
     * The validateToken method is used to validate the token.
     * 
     * @param token       the token
     * @param userDetails the user details
     * @return true if the token is valid, false otherwise
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * The GenerateToken method is used to generate a token.
     * 
     * @param username the username of the user
     * @return the token
     */
    public String GenerateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }
}
