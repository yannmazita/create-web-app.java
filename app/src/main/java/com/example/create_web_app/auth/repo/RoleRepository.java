
package com.example.create_web_app.auth.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.create_web_app.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(String name);
}
