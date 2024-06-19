package com.example.create_web_app.auth.service;

import com.example.create_web_app.auth.model.CustomUserDetails;
import com.example.create_web_app.users.model.User;
import com.example.create_web_app.users.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);

        if (user == null) {
            logger.error("Username not found: ");
            throw new UsernameNotFoundException("Couldn't find user for authentication");
        }
        logger.info("User " + username + " has been authenticated.");
        return new CustomUserDetails(user);
    }
}
