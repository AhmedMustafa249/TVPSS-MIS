package com.example.tvpssmis4.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gpm")
public class GPMController {

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        // Check if user is logged in and has correct role
        if (session.getAttribute("role") == null ||
                !session.getAttribute("role").equals("GPM")) {
            return "redirect:/login";
        }

        model.addAttribute("username", session.getAttribute("username"));
        return "/GPM/dashboard";
    }
}