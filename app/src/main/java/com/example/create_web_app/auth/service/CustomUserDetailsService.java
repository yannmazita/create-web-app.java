package com.example.create_web_app.auth.service;

import com.example.create_web_app.auth.dto.CustomUserDetails;
import com.example.create_web_app.users.model.User;
import com.example.create_web_app.users.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Fetches user details
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * The loadUserByUsername method is used to load user details by username.
     * 
     * @param username the username of the user
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Couldn't find user for authentication");
        }
        return new CustomUserDetails(user);
    }
}
