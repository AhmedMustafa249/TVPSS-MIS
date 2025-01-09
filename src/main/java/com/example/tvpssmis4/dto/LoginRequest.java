package com.example.tvpssmis4.dto;

import jakarta.validation.constraints.NotEmpty;

public class LoginRequest {
    @NotEmpty
    private String usernameOrEmail;

    @NotEmpty
    private String password;

    // Getters and Setters
    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
