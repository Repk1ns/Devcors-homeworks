package com.devcors.javaacademy.lesson6.data.repository;

import com.devcors.javaacademy.lesson6.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
