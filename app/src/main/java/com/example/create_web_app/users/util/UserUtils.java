package com.example.create_web_app.users.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.create_web_app.users.dto.UserCreate;
import com.example.create_web_app.users.dto.UserRead;
import com.example.create_web_app.users.model.User;

/**
 * Utility class for user-related operations.
 */
public class UserUtils {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserUtils(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Converts a user DTO to a User entity.
     * 
     * @param data user data.
     * @return User entity.
     */
    public User convertToUser(UserCreate data) {
        User user = new User();
        user.setUsername(data.getUsername());
        user.setHashedPassword(passwordEncoder.encode(data.getPassword()));
        return user;
    }

    /**
     * Converts a User entity to a user DTO.
     * 
     * @param data user data.
     * @return UserRead DTO.
     */
    public UserRead convertToUserRead(User data) {
        UserRead userRead = new UserRead();
        userRead.setId(data.getId());
        userRead.setUsername(data.getUsername());
        userRead.setRoles(data.getRoles());
        return userRead;
    }
}
