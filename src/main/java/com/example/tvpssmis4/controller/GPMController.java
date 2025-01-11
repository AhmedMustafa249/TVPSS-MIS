package com.example.tvpssmis4.controller;

import com.example.tvpssmis4.model.User;
import com.example.tvpssmis4.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gpm")
public class GPMController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        String email = (String) session.getAttribute("email");
        User user = userRepository.findByUsernameOrEmail(username, email);
        // Check if user is logged in and has correct role
        if (session.getAttribute("role") == null ||
                !session.getAttribute("role").equals("GPM")) {
            return "redirect:/login";
        }


        model.addAttribute("user", user);
        return "/GPM/dashboard";
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        // Check if user is logged in and has correct role
        if (session.getAttribute("role") == null ||
                !session.getAttribute("role").equals("GPM")) {
            return "redirect:/login";
        }

        model.addAttribute("username", session.getAttribute("username"));
        return "/GPM/home";
    }


}
