package com.example.tvpssmis4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/jpnj")
class JpnjController {

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";
    }
}