package com.example.tvpssmis4.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ppd")
public class PPDController {

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (session.getAttribute("role") == null ||
                !session.getAttribute("role").equals("PPD")) {
            return "redirect:/login";
        }

        model.addAttribute("username", session.getAttribute("username"));
        return "PPD/dashboard";
    }
}