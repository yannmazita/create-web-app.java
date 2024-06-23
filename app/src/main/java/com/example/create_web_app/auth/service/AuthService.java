package com.example.create_web_app.auth.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

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

        String scope = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(access_token_expires)
                .subject(authentication.getName())
                .claim("scopes", scope)
                .build();

        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return accessToken;
    }
}
