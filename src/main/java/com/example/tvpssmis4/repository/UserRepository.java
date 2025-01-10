package com.example.tvpssmis4.repository;

import com.example.tvpssmis4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameOrEmail(String username, String email);

    Optional<User> findByUsername(String username);
}
