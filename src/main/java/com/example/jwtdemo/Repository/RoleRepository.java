package com.example.jwtdemo.Repository;

import com.example.jwtdemo.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
