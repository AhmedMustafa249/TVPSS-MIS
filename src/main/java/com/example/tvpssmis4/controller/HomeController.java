package com.example.tvpssmis4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        if (session.getAttribute("role") == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", session.getAttribute("username"));
        return "/home";
    }

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("title", "Test Page");
        model.addAttribute("message", "This is a test page");
        return "test";
    }
}
