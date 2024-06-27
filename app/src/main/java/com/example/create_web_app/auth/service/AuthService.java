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
 * The AuthService class is responsible for generating and validating JWT
 * tokens.
 */
@Component
public class AuthService {
    @Autowired
    private JwtEncoder jwtEncoder;

    /**
     * The generateAccessToken method is used to generate an access token.
     * 
     * @param authentication the authentication
     * @return the access token
     */
    public String generateAccessToken(Authentication authentication) {
        Instant now = Instant.now();
        long expire = Long.parseLong(System.getenv("ACCESS_TOKEN_EXPIRE_MINUTES")) * 1000 * 60;
        Instant access_token_expires = now.plus(expire, ChronoUnit.MINUTES);

        /**
         * Update this part when implementing user registration. Unregistered users
         * won't be able to authenticate since they have no account.
         */
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

    private boolean isTokenExpired(String token) {
        JWTClaimsSet claims = extractClaims(token);
        return claims.getExpirationTime().before(new Date());
    }

    public String extractUsername(String token) {
        JWTClaimsSet claims = extractClaims(token);
        return claims.getSubject();
    }

    public String extractScopes(String token) {
        JWTClaimsSet claims = extractClaims(token);
        return claims.getClaim("scopes").toString();
    }

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
