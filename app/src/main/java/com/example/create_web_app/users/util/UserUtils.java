package com.example.create_web_app.users.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.create_web_app.users.dto.UserCreate;
import com.example.create_web_app.users.dto.UserRead;
import com.example.create_web_app.users.model.User;
import com.example.create_web_app.users.repo.UserRepository;
import com.example.create_web_app.users.service.UserService;

/**
 * Utility class for user-related operations.
 */
@Component
public class UserUtils {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserUtils(PasswordEncoder passwordEncoder, UserRepository userRepository, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userService = userService;
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

    public void createSuperUser() {
        User superUser = new User();
        superUser.setUsername("admin");
        superUser.setHashedPassword(passwordEncoder.encode("secret"));
        superUser.setRoles("admin");
        userRepository.save(superUser);
    }

    public void createFakeUsers() {
        for (int i = 0; i < 40; i++) {
            User fake_user = new User();
            fake_user.setUsername("fake_user_" + i);
            fake_user.setHashedPassword(passwordEncoder.encode("password"));
            if (i % 2 == 0) {
                fake_user.setRoles("user:own websockets");
            }
            userRepository.save(fake_user);
        }
    }
}
