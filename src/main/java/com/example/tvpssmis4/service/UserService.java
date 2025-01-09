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

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean login(LoginRequest loginRequest) {
        User myUser = userRepository.findByUsernameOrEmail(
                loginRequest.getUsernameOrEmail(), loginRequest.getUsernameOrEmail());
        if (myUser != null && passwordEncoder.matches(loginRequest.getPassword(), myUser.getPassword())) {
            return true;
        }
        return false;
    }

    public void register(RegisterRequest registerRequest) {
        User myUser = new User();
        myUser.setUsername(registerRequest.getUsername());
        myUser.setEmail(registerRequest.getEmail());
        myUser.setPhoneNumber(registerRequest.getPhoneNumber());
        myUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        logger.info("User successfully registered: {}", myUser.getUsername()); // Log the username

        userRepository.save(myUser);
    }
}
