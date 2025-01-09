package com.example.tvpssmis4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tvpssmis4.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Additional query methods (if needed) can be defined here
}