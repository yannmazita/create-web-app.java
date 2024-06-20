package com.example.create_web_app.auth.model;

import com.example.create_web_app.users.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The CustomUserDetails class is responsible for fetching user details from the
 * database.
 */
public class CustomUserDetails implements UserDetails {

    /**
     * The user field is used to store the user details.
     */
    private final User user;

    /**
     * The CustomUserDetails constructor is used to create an instance of the
     * CustomUserDetails class.
     * 
     * @param user the user details
     */
    public CustomUserDetails(User user) {
        this.user = user;
    }

    /**
     * The getAuthorities method is used to get the authorities of the user.
     * 
     * @return the authorities
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
     * The getPassword method is used to get the hashed password of the user.
     * 
     * @return the user's hashed password
     */
    @Override
    public String getPassword() {
        return this.user.getHashedPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }
}
