package com.example.tvpssmis4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to TVPSS MIS");
        return "home";
    }

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("title", "Test Page");
        model.addAttribute("message", "This is a test page");
        return "test";
    }

}