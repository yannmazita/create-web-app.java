package com.example.create_web_app.users.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.create_web_app.users.dto.UserCreate;
import com.example.create_web_app.users.dto.UserRead;
import com.example.create_web_app.users.dto.UserUpdate;
import com.example.create_web_app.users.model.User;
import com.example.create_web_app.users.repo.UserRepository;
import com.example.create_web_app.users.service.UserService;
import com.example.create_web_app.users.util.UserUtils;

/**
 * Handles user-related requests.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserUtils userUtils;

    @PreAuthorize("hasRole('admin')")
    @PostMapping
    public UserRead createUser(@RequestBody UserCreate data) {
        UserRead newUser = userUtils.convertToUserRead(userRepository.save(userUtils.convertToUser(data)));
        return newUser;
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/id/{id}")
    public UserRead getUserById(@PathVariable UUID id) {
        UserRead user = userUtils.convertToUserRead(userRepository.findById(id).orElse(null));
        return user;
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/all")
    public List<UserRead> getAllUsers(@RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "0") int limit) {
        List<User> users = userRepository.findAll();
        return null;
    }

    @PreAuthorize("hasRole('admin')")
    @PutMapping("/id/{id}")
    public UserRead updateUserById(@PathVariable UUID id, @RequestBody UserUpdate data) {
        return null;
    }

    @PreAuthorize("hasRole('admin')")
    @PatchMapping("/id/{id}/username")
    public UserRead updateUserUsernameById(@PathVariable UUID id, @RequestBody UserUpdate data) {
        return null;
    }

    @PreAuthorize("hasRole('admin')")
    @PatchMapping("/id/{id}/roles")
    public UserRead updateUserRolesById(@PathVariable UUID id, @RequestBody UserUpdate data) {
        return null;
    }

    @PreAuthorize("hasRole('user:own')")
    @GetMapping("/me")
    public UserRead getOwnUser() {
        return null;
    }

    @PreAuthorize("hasRole('user:own')")
    @DeleteMapping("/me")
    public UserRead deleteOwnUser() {
        return null;
    }

    @PreAuthorize("hasRole('user:own')")
    @PatchMapping("/me/password")
    public UserRead updateOwnPassword(@RequestBody UserUpdate data) {
        return null;
    }
}
