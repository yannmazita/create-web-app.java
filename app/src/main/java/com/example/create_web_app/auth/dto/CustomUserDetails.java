package com.example.create_web_app.auth.dto;

import com.example.create_web_app.users.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Stores user details.
 */
public class CustomUserDetails implements UserDetails {

    /**
     * User object.
     */
    private final User user;

    /**
     * Constructor for CustomUserDetails.
     * 
     * @param user the user object.
     */
    public CustomUserDetails(User user) {
        this.user = user;
    }

    /**
     * Gets all the roles granted to the user.
     * 
     * @return the roles granted.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*
         * return this.user.getRoles().stream()
         * .map(role -> new SimpleGrantedAuthority(role.getName()))
         * .collect(Collectors.toList());
         */
        String[] roles = this.user.getRoles().split(" ");
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

    /**
     * Gets the hashed password of the user.
     * 
     * @return the hashed password.
     */
    @Override
    public String getPassword() {
        return this.user.getHashedPassword();
    }

    /**
     * Gets the username of the user.
     * 
     * @return the username.
     */
    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    /**
     * Checks if the user account is not expired.
     * 
     * @return true if the account is not expired, false otherwise.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Checks if the user account is not locked.
     * 
     * @return true if the account is not locked, false otherwise.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Checks if the user credentials are not expired.
     * 
     * @return true if the credentials are not expired, false otherwise.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Checks if the user is enabled.
     * 
     * @return true if the user is enabled, false otherwise.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
