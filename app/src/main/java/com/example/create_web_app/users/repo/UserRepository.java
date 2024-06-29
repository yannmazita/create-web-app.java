package com.example.create_web_app.users.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.create_web_app.users.model.User;

/**
 * Repository for user entity
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
}
