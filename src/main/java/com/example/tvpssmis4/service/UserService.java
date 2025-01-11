package com.example.tvpssmis4.service;

import com.example.tvpssmis4.dto.LoginRequest;
import com.example.tvpssmis4.dto.RegisterRequest;
import com.example.tvpssmis4.model.User;
import com.example.tvpssmis4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User login(LoginRequest loginRequest) {
        User myUser = userRepository.findByUsernameOrEmail(
                loginRequest.getUsernameOrEmail(), loginRequest.getUsernameOrEmail());
        if (myUser != null && passwordEncoder.matches(loginRequest.getPassword(), myUser.getPassword())) {
            return myUser;
        }
        return null;
    }

    public void register(RegisterRequest registerRequest) {
        User myUser = new User();
        myUser.setUsername(registerRequest.getUsername());
        myUser.setEmail(registerRequest.getEmail());
        myUser.setPhoneNumber(registerRequest.getPhoneNumber());
        myUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        myUser.determineRole(registerRequest.getUsername());

        logger.info("User successfully registered: {}", myUser.getUsername()); // Log the username

        userRepository.save(myUser);
    }

    public boolean sendResetPasswordLink(String email) {
        // Check if a user exists with the given email
        User user = userRepository.findByEmail(email);
        if (user != null) {
            // Simulate sending a reset password link (You can integrate an email service here)
            System.out.println("Reset password link sent to: " + email);
            return true;
        } else {
            // No user found with the provided email
            System.out.println("No account found with email: " + email);
            return false;
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }


    public List<User> getAllUsers() {
        return userRepository.findAll(); // Fetches all users from the database
    }

    public void updateUser(User user) {
        userRepository.save(user); // Save the updated user in the database
    }




}
