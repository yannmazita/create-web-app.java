
package com.example.create_web_app.users.service;

import com.example.create_web_app.users.dto.UserUpdate;
import com.example.create_web_app.users.model.User;
import com.example.create_web_app.users.repo.UserRepository;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for user-related operations.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Updates a user's username.
     *
     * Dedicated method for username updates. If other data besides username is
     * present, it is ignored.
     * 
     * @param id   the user's ID.
     * @param data the new username.
     * @return the updated user.
     */
    public User updateUserUsername(UUID id, UserUpdate data) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(data.getUsername().toString());
            return userRepository.save(user);
        }
        return null;
    }

    /**
     * Updates a user's password.
     *
     * Dedicated method for password updates. If other data besides password
     * information is present, it is ignored.
     * 
     * @param id   the user's ID.
     * @param data the new password.
     * @return the updated user.
     */
    public User updateUserPassword(UUID id, UserUpdate data) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setHashedPassword(passwordEncoder.encode(data.getNewPassword().toString()));
            return userRepository.save(user);
        }
        return null;
    }

    /**
     * Updates a user's roles.
     *
     * Dedicated method for role updates. If other data besides roles is present, it
     * is ignored.
     * 
     * @param id   the user's ID.
     * @param data the new roles.
     * @return the updated user.
     */
    public User updateUserRoles(UUID id, UserUpdate data) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setRoles(data.getRoles().toString());
            return userRepository.save(user);
        }
        return null;
    }
}
