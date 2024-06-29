package com.example.create_web_app.auth.service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

/**
 * Service class for token-related operations.
 */
@Component
public class AuthService {
    @Autowired
    private JwtEncoder jwtEncoder;

    /**
     * Generates an access Token.
     * 
     * @param authentication Authentication object for authenticated users.
     * @return the access token.
     */
    public String generateAccessToken(Authentication authentication) {
        Instant now = Instant.now();
        long expire = Long.parseLong(System.getenv("ACCESS_TOKEN_EXPIRE_MINUTES")) * 1000 * 60;
        Instant access_token_expires = now.plus(expire, ChronoUnit.MINUTES);

        String scopes = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(access_token_expires)
                .subject(authentication.getName())
                .claim("scopes", scopes)
                .build();

        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return accessToken;
    }

    /**
     * Extracts all claims from a token.
     * 
     * @param token the token extract claims from.
     * @return the claims.
     */
    private JWTClaimsSet extractClaims(String token) {
        SignedJWT signedJWT;
        try {
            signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks if a token is expired.
     * 
     * @param token the token to check.
     * @return true if the token is expired, false otherwise.
     */
    private boolean isTokenExpired(String token) {
        JWTClaimsSet claims = extractClaims(token);
        return claims.getExpirationTime().before(new Date());
    }

    /**
     * Extracts the username from a token.
     * 
     * @param token the token to extract the username from.
     * @return the username.
     */
    public String extractUsername(String token) {
        JWTClaimsSet claims = extractClaims(token);
        return claims.getSubject();
    }

    /**
     * Extracts the scopes from a token.
     * 
     * @param token the token to extract the scopes from.
     * @return the scopes.
     */
    public String extractScopes(String token) {
        JWTClaimsSet claims = extractClaims(token);
        return claims.getClaim("scopes").toString();
    }

    /**
     * Validates a token.
     * 
     * @param token       the token to validate.
     * @param userDetails the user details to validate the token against.
     * @return true if the token is valid, false otherwise.
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        SignedJWT signedJWT;
        try {
            signedJWT = SignedJWT.parse(token);
        } catch (ParseException e) {
            return false;
        }
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
