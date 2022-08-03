package com.example.jwtdemo.Repository;

import com.example.jwtdemo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String userName);

}
